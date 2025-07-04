package ui;

import java.awt.BorderLayout;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Set;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import lombok.extern.apachecommons.CommonsLog;
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
@CommonsLog
public class OpenStreetMapView implements IMapViewer{
    private JXMapViewer mapViewer;
    private DefaultWaypoint selectedLocation;
    private GeoPosition selectedCoordinates;
    
    @Override
    public void initMap(JPanel mainPanel) {
        mainPanel.setLayout(new BorderLayout());
        
        mapViewer = new JXMapViewer();
        
        TileFactoryInfo info = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(info);
        mapViewer.setTileFactory(tileFactory);
        mapViewer.setZoom(8);

        GeoPosition gye = new GeoPosition(-2.163495, -79.877327);
        mapViewer.setAddressLocation(gye);

        initControllers();

        mainPanel.add(mapViewer, BorderLayout.CENTER);
        log.info("Map loaded correctly");
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
                    log.info("New point seleted");
                    // Convertir píxeles a coordenadas geográficas
                    Point p = e.getPoint();
                    GeoPosition geo = mapViewer.convertPointToGeoPosition(p);
                    selectedCoordinates = geo;

                    // Actualizar el waypoint seleccionado
                    updateSelectedLocation(geo);

                }
            }
        });
    }
    private void updateSelectedLocation(GeoPosition position) {
        // Crear nuevo waypoint para la ubicación seleccionada
        selectedLocation = new DefaultWaypoint(position);
        
        Set<Waypoint> waypoints = Set.of(selectedLocation);
        
        // Crear una capa de waypoints
        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
        waypointPainter.setWaypoints(waypoints);
        
        // Crear un pintador compuesto (por si quieres añadir más capas)
        List<WaypointPainter<Waypoint>> painters = List.of(waypointPainter);
        
        // Establecer el pintador compuesto en el mapa
        CompoundPainter<JXMapViewer> painter = new CompoundPainter<>(painters);
        mapViewer.setOverlayPainter(painter);
        
        // Repintar el mapa
        mapViewer.repaint();
    }

    @Override
    public GeoPosition getSelectedCoordinates() {
        return selectedCoordinates;
    }

}
