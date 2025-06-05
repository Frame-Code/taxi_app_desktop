package UI;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Painter;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

/**
 *
 * @author Daniel Mora Cantillo
 */
public class OpenStreetMapView implements IMapViewer{

    private JXMapViewer mapViewer;
    private DefaultWaypoint selectedLocation;
    private JLabel coordinatesLabel;
    private GeoPosition selectedCoordinates;

    
    @Override
    public void initMap(JPanel mainPanel) {
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);

        mapViewer = new JXMapViewer();
        mapViewer.setTileFactory(tileFactory);
        mapViewer.setZoom(8);

        GeoPosition gye = new GeoPosition(-79.877327, -2.163495);
        mapViewer.setAddressLocation(gye);

        initControllers();

        mainPanel.add(mapViewer, BorderLayout.CENTER);
        System.out.println("Everything");
    }

    private void initControllers() {
        MouseInputListener mia = new PanMouseInputListener(mapViewer);
        mapViewer.addMouseListener(mia);
        mapViewer.addMouseMotionListener(mia);
        mapViewer.addMouseListener(new CenterMapListener(mapViewer));
        mapViewer.addMouseWheelListener(new ZoomMouseWheelListenerCursor(mapViewer));
        mapViewer.addKeyListener(new PanKeyListener(mapViewer));

        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    // Convertir píxeles a coordenadas geográficas
                    Point p = e.getPoint();
                    GeoPosition geo = mapViewer.convertPointToGeoPosition(p);
                    selectedCoordinates = geo;

                    // Actualizar el waypoint seleccionado
                    updateSelectedLocation(geo);

                    // Mostrar las coordenadas en la etiqueta
                    coordinatesLabel.setText(String.format(
                            "Latitud: %.6f, Longitud: %.6f",
                            geo.getLatitude(),
                            geo.getLongitude()));
                    System.out.println("Coordinates changed");

                }
            }
        });
    }
    private void updateSelectedLocation(GeoPosition position) {
        // Crear nuevo waypoint para la ubicación seleccionada
        selectedLocation = new DefaultWaypoint(position);
        
        // Crear conjunto de waypoints
        Set<Waypoint> waypoints = new HashSet<>();
        waypoints.add(selectedLocation);
        
        // Crear una capa de waypoints
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);
        
        // Crear un pintador compuesto (por si quieres añadir más capas)
        List<Painter<JXMapViewer>> painters = new ArrayList<>();
        painters.add((Painter<JXMapViewer>) waypointPainter);
        
        // Establecer el pintador compuesto en el mapa
        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>();
        mapViewer.setOverlayPainter(painter);
        
        // Repintar el mapa
        mapViewer.repaint();
    }

    @Override
    public GeoPosition getSelectedCoordinates() {
        return selectedCoordinates;
    }

}
