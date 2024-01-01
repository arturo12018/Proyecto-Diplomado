package dgtic.core.proyecto.util;

import dgtic.core.proyecto.entity.Compra;
import dgtic.core.proyecto.entity.CompraLibro;
import dgtic.core.proyecto.repository.CompraLibroRepository;
import dgtic.core.proyecto.repository.CompraRepository;
import jakarta.mail.Message;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;



public class EnviarCorreo {

    //Correo de gmail
    static final String  gmail="";
    //Es de contraseña de aplicacion
    static final String pswd="";



    public static void enviar(Compra compra, List<CompraLibro> productos,String correo){


        Properties p=System.getProperties();
        p.setProperty("mail.smtps.host","smpt.gmail.com");
        p.setProperty("mail.smtps.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        p.setProperty("mail.smtps.socketFactory.fallback","false");
        p.setProperty("mail.smtp.port","465");
        p.setProperty("mail.smtp.socketFactory.port","465");
        p.setProperty("mail.smtps.auth","true");
        p.setProperty("mail.smtp.ssl.trust","smtp.gmail.com");
        p.setProperty("mail.smtps.ssl.trust","smtp.gmail.com");
        p.setProperty("mail.smtp.ssl.quitwait","false");
        //construcción del html
        String cadena="<h2>Ticket de compra:</h2>";
        SimpleDateFormat f=new SimpleDateFormat("dd/MM/yyyy");
        cadena+="<h2> Fecha de compra: "+f.format(compra.getFecha())+"</h2>";
        cadena+="<h2> Número de compra: "+compra.getId()+"</h2>";
        cadena+="<h2> Dirección de compra "+compra.getDireccion()+"</h2>";
        cadena+="<h2> País: "+compra.getPais().getNombre()+"</h2>";
        cadena+="<h2> Estado: "+compra.getEstado().getNombre()+"</h2>";
        for (
                CompraLibro s:productos ) {
            cadena+="<h2>"+s.getLibro().getTitulo()+"|"+
                    s.getLibro().getIsbn()+"|"+
                    s.getLibro().getPrecio()+"</h2></br>";

        }
        cadena+="<h2> Total: "+compra.getTotal()+"</h2>";
        try{
            Session session=Session.getInstance(p,null);
            MimeMessage message=new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correo,false));
            message.setSubject("Ticket de compra "+compra.getId());
            message.setContent(cadena,"text/html");
            message.setSentDate(new Date());

            Transport transport=(Transport)session.getTransport("smtps");
            transport.connect("smtp.gmail.com", gmail,pswd);
            transport.sendMessage(message,message.getAllRecipients());
            transport.close();

        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
