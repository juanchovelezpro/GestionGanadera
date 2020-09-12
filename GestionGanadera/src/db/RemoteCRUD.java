package db;

import java.net.UnknownHostException;

import model.Licencia;
import model.Usuario;
import tools.HTTPSUtils;
import tools.SystemMotherBoardNumber;
import tools.HTTPSUtils.OnResponseListener;

public class RemoteCRUD implements OnResponseListener {

	private HTTPSUtils utils;

	public RemoteCRUD() {

		utils = new HTTPSUtils();

		utils.setListener(this);

	}

	public void registrarUsuario(Usuario user){

		utils.PUTrequest(0, "https://gestionganadera-e6024.firebaseio.com/usuarios/" + user.getSerialNumber() + ".json",
				user.toJson());

	}

	@Override
	public void onResponse(int callbackID, String response) {

		switch (callbackID) {
		case 0:
			System.out.println(response);
			break;

		default:
			break;
		}

	}



}
