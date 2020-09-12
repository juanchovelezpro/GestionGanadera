package db;

import javax.swing.JOptionPane;

import com.google.gson.Gson;

import model.Licencia;
import model.Usuario;
import tools.HTTPSUtils;
import tools.HTTPSUtils.OnResponseListener;
import tools.SystemMotherBoardNumber;

public class RemoteCRUD implements OnResponseListener {

	private HTTPSUtils utils;
	private boolean existeLicencia;

	public RemoteCRUD() {

		existeLicencia = false;

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

	@Override
	public void onResponse(int callbackID, String response) {

		switch (callbackID) {
		case 0:
			System.out.println(response);
			break;

		case 1:

			Gson gson = new Gson();

			Licencia lic = gson.fromJson(response, Licencia.class);

			if (lic != null) {

				if (lic.getUsada().equalsIgnoreCase("NO")) {

					lic.setUsuario(SystemMotherBoardNumber.getSystemMotherBoard_SerialNumber());
					lic.setUsada("SI");

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

			break;

		default:
			break;
		}

	}

}
