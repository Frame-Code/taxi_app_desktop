package ui;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;

import javax.swing.JPanel;
import java.awt.BorderLayout;

public class MapViewerImpl implements IMapViewer {

    private GeoPosition selectedPosition = new GeoPosition(-2.170998, -79.922359); // Ubicación de Guayaquil por defecto
    private JXMapViewer mapViewer;

    @Override
    public GeoPosition getSelectedCoordinates() {
        return selectedPosition;
    }

    @Override
    public void initMap(JPanel mainPanel) {
        // Crear visor de mapa
        mapViewer = new JXMapViewer();

        // Configurar proveedor de tiles (OpenStreetMap)
        OSMTileFactoryInfo tileFactoryInfo = new OSMTileFactoryInfo();
        DefaultTileFactory tileFactory = new DefaultTileFactory(tileFactoryInfo);
        mapViewer.setTileFactory(tileFactory);

        // Establecer la posición y el zoom inicial
        mapViewer.setZoom(5);
        mapViewer.setAddressLocation(selectedPosition);

        // Añadir el visor de mapa al panel principal
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(mapViewer, BorderLayout.CENTER);
        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
