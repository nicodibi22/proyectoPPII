package proyecto.controladores;

import proyecto.vistas.Home;

public class Dispatcher {

	//private StudentView studentView;
    private Home homeView;
   
    public Dispatcher(){
       
       homeView = new Home();
    }

    public void dispatch(String request){
       if(request.equalsIgnoreCase("RESTO")){
          
       }
       else{
          homeView.iniciarVista();
       }	
   }
}
