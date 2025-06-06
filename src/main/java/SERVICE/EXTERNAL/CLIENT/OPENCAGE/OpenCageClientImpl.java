package SERVICE.EXTERNAL.CLIENT.OPENCAGE;

import com.opencagedata.jopencage.JOpenCageGeocoder;
import com.opencagedata.jopencage.model.JOpenCageResponse;
import com.opencagedata.jopencage.model.JOpenCageReverseRequest;
import lombok.extern.apachecommons.CommonsLog;

/**
 *
 * @author Daniel Mora Cantillo
 */
@CommonsLog
public class OpenCageClientImpl implements IOpenCageClient{
    private static OpenCageClientImpl INSTANCE;
    private final JOpenCageGeocoder geoCoder;

    private OpenCageClientImpl() {
        this.geoCoder = new JOpenCageGeocoder("aff8cae2561f4b66a8939c4ad2c0ce8a");
    }
    
    public static synchronized OpenCageClientImpl getInstance() {
        if(INSTANCE != null) {
            return INSTANCE;
        }
        return new OpenCageClientImpl();
    }
    
    @Override
    public String format(double latitude, double longitude) {
        JOpenCageResponse response = geoCoder.reverse(new JOpenCageReverseRequest(latitude, longitude));
        log.info("Coordinates formated");
        return response.getResults().get(0).getFormatted();
    }
}
