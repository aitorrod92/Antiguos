package tareaglobal;

import java.sql.*;
import javax.swing.JOptionPane;

// Esta clase realiza las operaciones necesarias para obtener resultados que mostrar en la GUI
public class Operaciones {

    // Atributos del JDBC
    private Connection conexion;
    private Statement sentencia;
    private ResultSet resultado;
    
    // Sentencias que se ejecutan
    private String SentenciaDQL = "";
    private String SentenciaDML = "";
    
    // Resultados de las consultas
    private String ConsultaCompleta = "";
    
    // Cabecera de la tabla
    private final String CabeceraTabla = "Código\t\tNombre\t\tID_Localización\t\tID_Mánager";
    
    // Atributos de cada departamento
    private int Código;
    private String Nombre;
    private int Localización;
    private int Mánager;
   
    // Referencia a la clase "Interfaz"
    private final Interfaz interfaz = new Interfaz();
        
    //Getters y Setters necesarios
    public String getSentenciaDQL() {
        return SentenciaDQL;
    }
    
    public String getSentenciaDML() {
        return SentenciaDML;
    }
    
        public String getNombre() {
        return Nombre;
    }

    public int getLocalización() {
        return Localización;
    }

    public int getMánager() {
        return Mánager;
    }
      
    // Método orientado a la inserción. La realiza si es posible y devuelve un mensaje diferente en función de si se ha llevado a cabo o no
    public String Insertar(String Código, String Nombre, String Localización, String Mánager) throws SQLException, ClassNotFoundException {
        // La sentencia utiliza la información contenida en los campos, pasada como parámetros
        IniciarComponentes();
        SentenciaDML = "INSERT INTO Departamentos (Código, Nombre, ID_Localización, ID_Mánager) VALUES " + "(" + Código
               + ",'" + Nombre + "'," + Localización + "," + Mánager + ");";
        try {
            sentencia.executeUpdate(SentenciaDML);
        } catch (SQLException ex) {
            return "Inserción no realizada. Asegúrate de rellenar todos los campos y de no introducir un código ya existente";
        }
              
       BuscarTodo();
       ObtenerConsultaCompleta();
              LiberarRecursos();
      
       return CabeceraTabla + "\n" + ConsultaCompleta;
    }
    
    // Método orientado a la eliminación. Devuelve mensajes diferentes en función de si se realiza o no (y por qué no se realiza)
    public String Eliminar (String Código) throws SQLException, ClassNotFoundException {
        // Comprueba que "Código" no está vacío
        if (Código.equals("")){
            return "Eliminación no realizada. Introduce un código.";
        }
        
        // Realiza una consulta para comprobar que el registro existe
        IniciarComponentes();
        ConsultaPreviaAEliminación(Código);
                
        if (ConsultaCompleta.equals("")){
            return "Eliminación no realizada. No se ha encontrado ningún registro con código " + Código; 
        
        // Si el registro existe, pide confirmación de borrado al usuario. Si el usuario acepta, ejecuta el borrado
        } else {
            if (Confirmación() == 1){
            return "Eliminación no realizada (abortada)";
        } else {
            SentenciaDML = "DELETE FROM Departamentos WHERE Código = " + Código + ";";
            sentencia.executeUpdate(SentenciaDML);
        }
               
       BuscarTodo();       
       ObtenerConsultaCompleta();
              LiberarRecursos();
        
       return CabeceraTabla + "\n" + ConsultaCompleta;
        }
    }
        
    // Método llamado al realizar el primer click sobre el botón "Modificar". Su función es determinar que el código existe
    public String Modificar(String Código) throws SQLException, ClassNotFoundException{
        // Comprueba que "Código" no está vacío
        if (Código.equals("")){
            return "Modificación no realizada. Introduce un código.";
        }
        
        // Realiza una consulta para comprobar que el registro existe y devolver mensajes acordes
        IniciarComponentes();
        ConsultaPreviaAEliminación(Código);
               LiberarRecursos();
                
        if (ConsultaCompleta.equals("")){
            return "Modificación no realizada. No se ha encontrado ningún registro con código " + Código; 
        } else {
            return "Se ha encontrado un registro para el código " + Código + ".\nModifica los valores deseados y pulsa \"Modificar\" "
                    + "de nuevo para cambiarlos.";
        }               
    } 
     
    // Método llamado al realizar el segundo click sobre el botón "Modificar". Realiza la actualización de la tabla
    public String ModificarSegundoClick(String Código, String Nombre, String Localización, String Mánager) throws SQLException, ClassNotFoundException{
        // La sentencia utiliza la información contenida en los campos, pasada como parámetros
        IniciarComponentes();
        SentenciaDML = "UPDATE Departamentos SET Nombre = '" + Nombre + "', ID_Localización = " + Localización + ", ID_Mánager = " + Mánager 
                + " WHERE Código = " + Código;
        sentencia.executeUpdate(SentenciaDML);
        
        BuscarTodo();
        ObtenerConsultaCompleta();
               LiberarRecursos();
        
        return CabeceraTabla + "\n" + ConsultaCompleta;
    }
    
    // Método llamado al pulsar "Mostrar". Realiza consultas en función de los valores en los campos de texto, pasados como parámetros
    public String Mostrar(String Código, String Nombre, String Localización, String Mánager) throws SQLException, ClassNotFoundException{
      // Si todos los campos de texto están vacíos, se muestran todos los datos
      if (Código.equals("") && Nombre.equals("") && Localización.equals("") && Mánager.equals("")){
           BuscarTodo();
      } else {
     
     // Se examinan qué campos están vacios y cuáles no para construir la sentencia de consulta y lanzarla
     String ReferenciaCódigo = "";
     String ReferenciaNombre = "";
     String ReferenciaLocalización = "";
     String ReferenciaMánager = "";
      
      if (!Código.equals("")){
          ReferenciaCódigo = "Código = ";
      }
      
      if (!Nombre.equals("")){
          ReferenciaNombre = "Nombre = '";
                  if (!Código.equals("")){
                      ReferenciaNombre = " AND " + ReferenciaNombre;
      }
      }
               
      if (!Localización.equals("")){
          ReferenciaLocalización = "ID_Localización = ";
                if (!Nombre.equals("") || !Código.equals("")){
                      ReferenciaLocalización = " AND " + ReferenciaLocalización;
                      if (!Nombre.equals("")){
                          ReferenciaLocalización = "' " + ReferenciaLocalización;
                      }
            }
      } else if (!Nombre.equals("") && Mánager.equals("")) ReferenciaLocalización = "' ";
                
      if (!Mánager.equals("")){
          ReferenciaMánager = "ID_Mánager = ";    
          if (!Localización.equals("") || !Nombre.equals("") || !Código.equals("")){
                    ReferenciaMánager = " AND " + ReferenciaMánager;
                    if (!Nombre.equals("") && Localización.equals("")){
                        ReferenciaMánager = "'" + ReferenciaMánager;
                    }
            }
      }
       
        IniciarComponentes();
        SentenciaDQL = "SELECT * FROM Departamentos WHERE " + ReferenciaCódigo + Código + ReferenciaNombre + Nombre + ReferenciaLocalización +
                Localización + ReferenciaMánager + Mánager + ";";
        resultado = sentencia.executeQuery(SentenciaDQL);
      }
              
        ObtenerConsultaCompleta();
               LiberarRecursos();
        
        return CabeceraTabla + "\n" + ConsultaCompleta;
    }
    
    // Método que utilizan los métodos "Eliminar" y "Modificar" para comprobar que los datos existen antes de alterarlos
    public void ConsultaPreviaAEliminación(String Código) throws SQLException, ClassNotFoundException{
        IniciarComponentes();
        SentenciaDQL = "SELECT * FROM Departamentos WHERE Código = " + Código + ";";
        resultado = sentencia.executeQuery(SentenciaDQL);
        ObtenerConsultaCompleta();
      }
    

    // Método para obtener la tabla completa
    public void BuscarTodo() throws SQLException, ClassNotFoundException{
       IniciarComponentes();
       SentenciaDQL = "SELECT * FROM Departamentos;";
       resultado = sentencia.executeQuery(SentenciaDQL);
    }
    
    /* Método para pasar a String la tabla almacenada en "resultado". Excepto cuando se apliquen filtros en el método "Mostrar", siempre va a ir
    precedida de "BuscarTodo" y, por tanto, mostrará la tabla entera. Además, actualiza los valores, de los atributos del departamento para poder
    ser mostrados en el panel de confirmación de "Confirmación" cuando se va a eliminar un registro*/
    public void ObtenerConsultaCompleta() throws SQLException{
        ConsultaCompleta = "";

        while(resultado.next()){
            String Registro = resultado.getInt(1)+"\t\t"+ resultado.getString(2) + "\t\t" + resultado.getInt(3) + "\t\t" + resultado.getInt(4);
            Código = resultado.getInt(1);
            Nombre = resultado.getString(2);
            Localización = resultado.getInt(3);
            Mánager = resultado.getInt(4);            
            
            /* Determina si "Resultado final" no está vacío (lo que indica que no se trata de la primera iteración) y
             en tal caso, añade un salto de línea para separar con respecto a lo ya escrito */
            ConsultaCompleta = !ConsultaCompleta.equals("")? ConsultaCompleta + "\n" + Registro : ConsultaCompleta + Registro;
            }
   }
    
    // Lllamado desde "Eliminar", muestra un panel de confirmación y devuelve la respuesta
    public int Confirmación() throws SQLException{
      int Diálogo;
      Object[] opciones = {"Sí", "No"};
       Diálogo= JOptionPane.showOptionDialog(interfaz.getRootPane(), "¿Desea eliminar el departamento? Código: " + 
       Código + " Nombre: " + Nombre +  " Localización: " + Localización +  " Mánager: " + Mánager, 
               "Confirmación de eliminación",JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, opciones, opciones[1]);
       return Diálogo;
    }
    
    // Carga el driver, establece la conexión y crea el objeto Statement
    public void IniciarComponentes() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost/51984699F", "root", "");
        sentencia = conexion.createStatement();
    }
        
    // Libera recursos
    public void LiberarRecursos() throws SQLException{
        resultado.close();
        sentencia.close();
        conexion.close();
    }  
}
