package presentacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class TablaDePosiciones {

	public JFrame frmTablaDePosiciones;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TablaDePosiciones window = new TablaDePosiciones();
					window.frmTablaDePosiciones.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TablaDePosiciones() {
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
		model.addColumn("Posicion");
		model.addColumn("Puntaje");

		// agrego filas con la info
		// String[] tablaPosiciones = juego.obtenerTablaDePosiciones();
		model.addRow(new String[] { "1", "1234" });
		model.addRow(new String[] { "2", "123" });
		model.addRow(new String[] { "3", "12" });

		table.setModel(model);

		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(JLabel.CENTER);

		for (int i = 0; i < table.getColumnCount(); i++) {
			TableColumn col = table.getColumnModel().getColumn(i);
			col.setCellRenderer(dtcr);
		}

	}
}
