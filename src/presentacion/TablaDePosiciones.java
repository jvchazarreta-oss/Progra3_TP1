package presentacion;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import negocio.Juego;

public class TablaDePosiciones {

	public JFrame frmTablaDePosiciones;
	private JTable table;
	private Juego juego;

	/**
	 * Create the application.
	 */
	public TablaDePosiciones(Juego juego) {
		this.juego = juego;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTablaDePosiciones = new JFrame();
		frmTablaDePosiciones.setTitle("Tabla de posiciones");
		frmTablaDePosiciones.setResizable(false);
		frmTablaDePosiciones.setBounds(100, 100, 333, 304);
		frmTablaDePosiciones.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTablaDePosiciones.getContentPane().setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 297, 243);
		frmTablaDePosiciones.getContentPane().add(scrollPane);

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setEnabled(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		scrollPane.setViewportView(table);

		// modelo
		DefaultTableModel model = new DefaultTableModel();

		// agrego columnas
		model.addColumn("Posición");
		model.addColumn("Puntaje");

		// agrego filas con la info
		ArrayList<Integer> puntajes = juego.obtenerPuntajes();

		for (int i = 0; i < puntajes.size(); i++) {
			Integer puntaje = puntajes.get(i);
			model.addRow(new String[] { String.valueOf(i + 1), puntaje.toString() });
		}

		table.setModel(model);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn col = table.getColumnModel().getColumn(i);
			col.setCellRenderer(dtcr);
		}

	}
}
