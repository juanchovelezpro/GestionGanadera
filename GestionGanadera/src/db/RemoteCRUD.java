package db;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import model.Licencia;
import model.Usuario;
import tools.HTTPSUtils;
import tools.HTTPSUtils.OnResponseListener;
import tools.SystemMotherBoardNumber;
import view.AgregarUsuarioDialog;

public class RemoteCRUD implements OnResponseListener {

	private HTTPSUtils utils;
	private Usuario user;
	

	public RemoteCRUD(Usuario user) {

		this.user = user;
		utils = new HTTPSUtils();

		utils.setListener(this);

	}

	public void registrarUsuario(Usuario user, String licencia) {

		utils.PUTrequest(0, "https://gestionganadera-e6024.firebaseio.com/usuarios/" + user.getSerialNumber() + ".json",
				user.toJson());

		existeLicencia(licencia);

	}

	public void existeLicencia(String licencia) {

		utils.GETrequest(1, "https://gestionganadera-e6024.firebaseio.com/licencias/" + licencia + ".json");

	}
	
	
	public void renovarLicenciaFinal(String licencia) {
		
		utils.PUTrequest(0, "https://gestionganadera-e6024.firebaseio.com/usuarios/" + user.getSerialNumber() + ".json",
				user.toJson());
		utils.GETrequest(2, "https://gestionganadera-e6024.firebaseio.com/licencias/" + licencia + ".json");

		
	}
	
	
	private void renovarLicencia(String response) {
		
		Gson gson2 = new Gson();

		Licencia lic2 = gson2.fromJson(response, Licencia.class);

		if (lic2 != null) {

			if (lic2.getUsada().equalsIgnoreCase("NO")) {

				lic2.setUsuario(SystemMotherBoardNumber.getSystemMotherBoard_SerialNumber());
				lic2.setUsada("SI");
				Usuario user2 = user;
				user2.setFechaLimite(AgregarUsuarioDialog.fechalimite());
				UsuarioCRUD.update(UsuarioCRUD.select().get(0).getNombre(), user2);
				utils.PUTrequest(0,
						"https://gestionganadera-e6024.firebaseio.com/licencias/" + lic2.getValor() + ".json",
						lic2.toJson());
				JOptionPane.showMessageDialog(null, "Su licencia ha sido renovada con exito \n Ingrese de nuevo al software" );
                System.exit(0);
				
			} else {

				JOptionPane.showMessageDialog(null, "Esta licencia ya se encuentra en uso");
                System.exit(0);

				

			}

		} else {

			JOptionPane.showMessageDialog(null, "Error con la licencia \nComuniquese con el administrador");
			System.exit(0);

		}
		
	}
	
	private void comprobarPrimeraVezLicencia(String response) {
		
		
		Gson gson = new Gson();

		Licencia lic = gson.fromJson(response, Licencia.class);

		if (lic != null) {

			if (lic.getUsada().equalsIgnoreCase("NO")) {

				lic.setUsuario(SystemMotherBoardNumber.getSystemMotherBoard_SerialNumber());
				lic.setUsada("SI");
				UsuarioCRUD.insert(user);
				utils.PUTrequest(0,
						"https://gestionganadera-e6024.firebaseio.com/licencias/" + lic.getValor() + ".json",
						lic.toJson());

			} else {

				JOptionPane.showMessageDialog(null, "Esta licencia ya se encuentra en uso");

			}

		} else {

			JOptionPane.showMessageDialog(null, "Error con la licencia \nComuniquese con el administrador");
			System.exit(0);

		}
	}

	@Override
	public void onResponse(int callbackID, String response) {

		switch (callbackID) {
		case 0:
			System.out.println(response);
			break;

		case 1:

			comprobarPrimeraVezLicencia(response);
			

			break;
		
		case 2:

			renovarLicencia(response);
			
			
			break;

		
		}

	}

}
