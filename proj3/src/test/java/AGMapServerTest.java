import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class AGMapServerTest {
    static List<TestParams> params;
    private static boolean initialized = false;
    static final double doubleThreshhold = 0.0000000000001;

    /**
     * Initializes the student MapServer statically.
     * Reads in the serialized <code>List</code> of TestParams.
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        if (initialized) return;
        MapServer.initialize();
        FileInputStream fis = new FileInputStream("test_ser_data");
        ObjectInputStream ois = new ObjectInputStream(fis);
        params = (List<TestParams>) ois.readObject();
        ois.close();
        initialized = true;
    }

    private void checkParamsMap(String err, Map<String, Object> m1, Map<String, Object> m2) {
        for (String key : m1.keySet()) {
            assertTrue(m2.containsKey(key));
            Object o1 = m1.get(key);
            Object o2 = m2.get(key);
            if (o1 instanceof Double) {
                assertTrue(err, Math.abs((Double)o1 - (Double)o2) < doubleThreshhold);
            } else {
                assertEquals(err, o1, o2);
            }
        }
    }

    /**
     * Test the rastering functionality of the student code, by writing the ByteArrayOutputStream
     * to a byte[] array and comparing it with the staff result byte-wise.
     * @throws Exception
     */
    @Test
    public void testGetMapRaster() throws Exception {
        MapServer.clearRoute();
        for (int i = 0; i < params.size(); i++) {
            TestParams p = params.get(i);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            MapServer.getMapRaster(p.raster_params, os);
            byte[] student_output = os.toByteArray();
            assertArrayEquals("Raw image output differed for input: " + p.raster_params + ".\n See " +
                    "example image " + i + ".\n", p.raster_output, student_output);
        }
    }

    /**
     * Check the student raster output parameters against the staff output parameters.
     * @throws Exception
     */
    @Test
    public void testGetMapRasterParams() throws Exception {
        for (TestParams p : params) {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            Map<String, Object> student_raster_result = MapServer.getMapRaster(p.raster_params, os);
            checkParamsMap("Returned parameters differed for input: " + p.raster_params + ".\n"
                    , p.raster_result, student_raster_result);
        }
    }


    /**
     * Test the routefinding functionality by comparing the node id list item by item.
     * @throws Exception
     */
    @Test
    public void testFindAndSetRoute() throws Exception {
        for (TestParams p : params) {
            List<Long> student_route_result = MapServer.findAndSetRoute(p.route_params);
            assertEquals("Found route differs for input: " + p.raster_params + ".\n",
                    p.route_result, student_route_result);
        }
    }

    /**
     * Test the route raster the same way the map raster is tested, except with the route pre-set
     * before making the call to raster.
     * @throws Exception
     */
    @Test
    public void testRouteRaster() throws Exception {
        for (int i = 0; i < params.size(); i++) {
            TestParams p = params.get(i);
            MapServer.findAndSetRoute(p.route_params);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            MapServer.getMapRaster(p.raster_params, os);
            byte[] student_output = os.toByteArray();
            assertArrayEquals("Raw image output differed for input: " + p.raster_params + "\nWith" +
                    " route params: " + p.route_params + ".\n See " +
                    "example image " + i + ".\n", p.route_raster, student_output);
        }
    }


    /**
     * Test Autocomplete for each prefix, comparing the sets of outputs against each other.
     * @throws Exception
     */
    @Test
    public void testGetLocationsByPrefix() throws Exception {
        for (TestParams p : params) {
            List<String> student_autocomplete_result = MapServer.getLocationsByPrefix(p
                    .prefix_search_param);
            HashSet<String> set_student = new HashSet<>(student_autocomplete_result);
            HashSet<String> set_staff = new HashSet<>(p.autocomplete_results);

            assertEquals("Autocompletion results differ for prefix " + p.prefix_search_param,
                    set_staff, set_student);
        }
    }

    /**
     * Test location search by full search string, comparing the output lists against each other
     * element by element; note that we assume the most reasonable construction of each of these
     * lists, that is, that they are in order of the locations as they appear in the OSM file.
     * @throws Exception
     */
    @Test
    public void testGetLocations() throws Exception {
        for (TestParams p : params) {
            List<Map<String, Object>> student_search_result = MapServer.getLocations(p.actual_search_param);
            assertEquals("Search results differ for search term: " + p.actual_search_param,
                    p.actual_search_result, student_search_result);
        }
    }
}