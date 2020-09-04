package view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.Res;

public class ModeloTablePurgado extends AbstractTableModel {

    private ArrayList<Res> reses;
    private String[] columna;
   

    public ModeloTablePurgado(ArrayList<Res> reses) {

        this.reses = new ArrayList<Res>(reses);
        

    }

    @Override
    public int getRowCount() {
        return reses.size();
    }

    @Override
    public int getColumnCount() {
        return 1;
    }
    
    public void setColumna(String[] columna) {
		this.columna = columna;
	}

    @Override
    public String getColumnName(int column) {
    	
    	return columna[column].toString();
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Object value = "??";
        Res user = reses.get(rowIndex);
        switch (columnIndex) {
            case 0:
                value = user.toPurgado();
                break;
          
        }

        return value;

    }

	public ArrayList<Res> getReses() {
		return reses;
	}

	public void setReses(ArrayList<Res> reses) {
		this.reses = reses;
	}
	
	
    
    

   

}