import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * TestParams fully describes the inputs and expected outputs for test cases.
 * After being generated (test-case autogeneration code not shown in this file),
 * this information is serialized for testing.
 * @author Alan Yao
 */
public class TestParams implements Serializable {
    static final long serialVersionUID = 7945479797638412654L;
    Map<String, Double> raster_params;
    Map<String, Double> route_params;
    String prefix_search_param;
    String actual_search_param;
    Map<String, Object> raster_result;
    byte[] raster_output;
    LinkedList<Long> route_result;
    byte[] route_raster;
    List<Map<String, Object>> actual_search_result;
    List<String> autocomplete_results;
}