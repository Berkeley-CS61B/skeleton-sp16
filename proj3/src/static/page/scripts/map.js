/**
  * Map project javascript file written for CS61B/CS61BL.
  * This is not an example of good javascript or programming practice.
  * Feel free to improve this front-end for your own personal pleasure.
  * Author: Alan Yao. If using, please credit me.
  **/


// Global variables
var params = {ullat: 37.88, ullon: -122.27625, lrlat: 37.83, lrlon: -122.22,
              w: $(window).width(), h: $(window).height()};
var route_params = {};
var map; var dest;
var tx = 0; var ty = 0;
var rtx; var rty;
var markers = [];
var host;
// Hardcoded hyper-parameters #machinelearning
var zoom_delta = 0.04; var base_move_delta = 0.03;
var current_level = 0;
var ullon_bound, ullat_bound, lrlon_bound, lrlat_bound;
var img_w, img_h;
var wdpp = 0.00004291534423828125; // Starting wdpp for level 3
var hdpp = 0.00003388335630702399; // Starting hdpp for level 3
var max_level = 7; var min_level = 2; // Level limits based on pulled data

// Compute lat and lon by window size
function real_lrlat() { return params["ullat"] - hdpp * params["h"]; }

function real_lrlon() { return params["ullon"] + wdpp * params["w"]; }

$( document ).ready(function() {
    if (document.location.hostname !== "localhost") {
        host = "http://" + document.location.host;
    } else {
        host = "http://localhost:4567"
    }
    var raster_server = host + "/raster"
    var route_server = host + "/route"
    var clear_route = host + "/clear_route"
    var search = host + "/search"
    map = document.getElementById("map");
    dest = document.getElementById("dest");
    dest.style.visibility = 'hidden';
    params["lrlon"] = real_lrlon();
    params["lrlat"] = real_lrlat();
    update();
    var $body = $('#mapbody');
    $('body').css("overflow", "hidden"); // hide scroll bar

    // Search bar
    $( "#tags" ).autocomplete({
          source: search,
          minLength: 2,
          select: function (event, ui) {
              $.get({
                  async: true,
                  url: search,
                  data: { term: ui.item.value, full: true},
                  success: function(data) {
                      removeMarkers();
                      for (var i = 0; i < data.length; i++) {
                          console.log(data[i]);
                          ele = $('<img/>', {
                              id: data[i].id,
                              src: 'round_marker.gif',
                              class: 'rmarker'
                          });
                          ele.appendTo($('#markers'));
                          markers.push({lat: data[i].lat, lon: data[i].lon,
                                        tx: 0, ty: 0, element: ele});
                      }
                      update();
                  },
                  dataType: "json"
              });
          }
    });

    // Enables drag functionality
    $body.on('mousedown', function (evt) {
      var startX = evt.pageX;
      var startY = evt.pageY;
      var tmpX = startX;
      var tmpY = startY;
      var moved = false;
      $body.on('mousemove', function handler(evt) {
        dx = evt.pageX - tmpX;
        dy = evt.pageY - tmpY;
        tx += dx;
        ty += dy;
        tmpX = evt.pageX;
        tmpY = evt.pageY;
        moved = true;
        updateT();
      });

      $body.on('mouseup', function handler(evt) {
        $body.off('mousemove');
        $body.off('mouseup');
        if (moved) {
            dx = evt.pageX - startX;
            dy = evt.pageY - startY;
            params["ullon"] -= dx * wdpp;
            params["lrlon"] -= dx * wdpp;
            params["ullat"] += dy * hdpp;
            params["lrlat"] += dy * hdpp;
            conditionalUpdate();
        }
      });
    });

    $('#clear').click(function handler(evt) {
        evt.preventDefault();
        $.get({
            async: true,
            url: clear_route,
            success: function(data) {
                dest.style.visibility = 'hidden';
                update();
            },
        });
    });

    $('body').dblclick(function handler(evt) {
        if (route_params["start_lon"] && route_params["end_lon"]) { //finished routing, reset routing
            route_params = {};
        }
        if (route_params["start_lon"]) { // began routing already but not finished
            route_params["end_lon"] = params["ullon"] + evt.pageX * wdpp;
            route_params["end_lat"] = params["ullat"] - evt.pageY * hdpp;
            updateRoute();
            dest.style.visibility = 'visible';
            update();
        } else {
            route_params["start_lon"] = params["ullon"] + evt.pageX * wdpp;
            route_params["start_lat"] = params["ullat"] - evt.pageY * hdpp;
        }
    });

    // Enables scroll wheel zoom
    $(window).bind('mousewheel DOMMouseScroll', function(event){
        if (event.originalEvent.wheelDelta > 0 || event.originalEvent.detail < 0) {
            zoomIn();
        } else {
            zoomOut();
        }
    });

    // Prevent image dragging
    $('img').on('dragstart', function(event) { event.preventDefault(); });

    // Allow for window resizing
    window.onresize = function(event) {
        params["w"] = $(window).width();
        params["h"] = $(window).height();
        params["lrlon"] = params["ullon"] + wdpp * params["w"];
        params["lrlat"] = params["ullat"] - hdpp * params["h"];
        update();
    };

    // Keyboard navigation callbacks
    document.onkeydown = function(e) {
        var delta = base_move_delta / (Math.pow(2, current_level));
        switch (e.keyCode) {
            case 37: //left
                shiftLeft(delta);
                update();
                break;
            case 38: //up
                shiftUp(delta);
                update();
                break;
            case 39: //right
                shiftRight(delta);
                update();
                break;
            case 40: //down
                shiftDown(delta);
                update();
                break;
            case 189: //minus
                zoomOut();
                break;
            case 187: //equals/plus
                zoomIn();
                break;
        }
    };

    function zoom(direction, level) {
        starting_level = current_level;
        // Adjust the zoom amount based on current amount of zoom
        delta = direction * zoom_delta / (Math.pow(2, level));
        // Account for aspect ratio
        window_ratio = params["w"] / params["h"];
        // Try several times in case didn't zoom enough
        for (i = 0; i < 3 && starting_level == current_level; i++) {
            params["ullat"] -= delta;
            params["ullon"] += delta * window_ratio;
            params["lrlat"] += delta;
            params["lrlon"] -= delta * window_ratio;
            update();
            // Adaptive search #machinelearning LOL
            delta /= 2;
        }
        params["lrlon"] = real_lrlon();
        params["lrlat"] = real_lrlat();
    }

    function zoomIn() {
        if (current_level == max_level) {
            return;
        }
        zoom(1, current_level);
    }

    function zoomOut() {
        if (current_level == min_level) {
            return;
        }
        zoom(-1, current_level - 1);
    }

    function shiftLeft(delta) {
        params["ullon"] -= delta;
        params["lrlon"] -= delta;
        tx -= delta * (1 / wdpp);
    }
    function shiftUp(delta) {
        params["ullat"] += delta;
        params["lrlat"] += delta;
        ty += delta * (1 / hdpp);
    }
    function shiftRight(delta) {
        params["ullon"] += delta;
        params["lrlon"] += delta;
        tx += delta * (1 / wdpp);
    }
    function shiftDown(delta) {
        params["ullat"] -= delta;
        params["lrlat"] -= delta;
        ty -= delta * (1 / hdpp);
    }


    function update() { updateImg(); updateT(); }

    function conditionalUpdate() {
        if (updateT()) {
            console.log("Update required.")
            update();
        }
    }

    function removeMarkers() {
        for (var i = 0; i < markers.length; i++) {
            markers[i].element.remove();
        }
        markers = [];
    }

    function updateMarkers() {
        for (var i = 0; i < markers.length; i++) {
            marker = markers[i];
            marker.tx = (marker.lon - params["ullon"]) * (1 / wdpp) - 7 - tx;
            marker.ty = - (marker.lat - params["ullat"]) * (1 / hdpp) - 7 - ty;
        }
    }

    function updateImg() {
        // Synchronous ajax call for image update. Could be async for better experience
        // but then user spam locks up the server.
        $.get({
            async: false,
            url: raster_server,
            data: params,
            success: function(data) {
                if (data.query_success) {
                    console.log("Updating map");
                    map.src = "data:image/png;base64," + data.b64_encoded_image_data;
                    ullon_bound = data.raster_ul_lon;
                    ullat_bound = data.raster_ul_lat;
                    lrlon_bound = data.raster_lr_lon;
                    lrlat_bound = data.raster_lr_lat;
                    current_level = data.depth;
                    img_w = data.raster_width;
                    img_h = data.raster_height;
                    wdpp = (lrlon_bound - ullon_bound) / img_w;
                    hdpp = (ullat_bound - lrlat_bound) / img_h;
                    // Compute initial transform
                    tx = - (params["ullon"] - ullon_bound) * (1 / wdpp);
                    ty = (params["ullat"] - ullat_bound) * (1 / hdpp);
                    rtx = (route_params["end_lon"] - params["ullon"]) * (1 / wdpp) - dest.width / 2 - tx;
                    rty = - (route_params["end_lat"] - params["ullat"]) * (1 / hdpp) - dest.height - ty;
                    updateMarkers();
                }
            },
            dataType: "json"
        });
    }

    function updateT() {
        map.style.transform = "translateX(" + tx + "px) translateY(" + ty + "px)";
        dest.style.transform = "translateX(" + (tx+rtx) + "px) translateY(" + (ty+rty) + "px)";
        for (var i = 0; i < markers.length; i++) {
            marker = markers[i];
            marker.element.css("transform", "translateX(" + (tx+marker.tx) + "px) translateY(" +
            (ty+marker.ty) + "px)");
        }
        // validate transform - true if img needs updating
        return params["ullon"] < ullon_bound || params["ullat"] > ullat_bound ||
            params["lrlon"] > lrlon_bound || params["lrlat"] < lrlat_bound;
    }

    function updateRoute() {
        $.get({
            async: true,
            url: route_server,
            data: route_params,
            success: function(data) {
                updateImg();
            },
        });
    }

});
