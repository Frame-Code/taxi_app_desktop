package UI;

import javax.swing.JPanel;
import org.jxmapviewer.viewer.GeoPosition;

/**
 *
 * @author Daniel Mora Cantillo
 */
public interface IMapViewer {
    GeoPosition getSelectedCoordinates();
    void initMap(JPanel mainPanel);
}
