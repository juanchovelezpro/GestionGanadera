package view;

import javax.swing.table.AbstractTableModel;

public class ModelTable extends AbstractTableModel {

	private String[] columns;
	private Object[][] data;

	@Override
	public String getColumnName(int column) {

		return columns[column].toString();
	}

	@Override
	public int getColumnCount() {

		return columns.length;
	}

	@Override
	public int getRowCount() {

		return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex].toString();
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public String[] getColumns() {
		return columns;
	}

	public void setColumns(String[] columns) {
		this.columns = columns;
	}

	public Object[][] getData() {
		return data;
	}

	public void setData(Object[][] data) {
		this.data = data;
	}

}
