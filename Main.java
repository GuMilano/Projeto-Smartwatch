import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;


public class SmartWatchApp {
    private double latitude;
    private double longitude;
    private int frequenciaCardiaca;
    private String nomePaciente = "João"; // Nome do paciente 

    public SmartWatchApp() {
        latitude = 0.0;
        longitude = 0.0;
        frequenciaCardiaca = 40; // Valor inicial dos batimentos cardíacos

        // Configuração do timer para atualizar a cada 1 minuto
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Timer executado!"); // Mensagem de Log
                String mensagemLocalizacao = atualizarLocalizacao();
                medirBatimentosCardiacos();
                enviarDadosParaServidor();
                // Mensagem que é exibida no SmartWatch
                exibirMensagem("Atualização", mensagemLocalizacao + "\n\nBatimentos cardíacos: " + frequenciaCardiaca + " BPM");
                if (frequenciaCardiaca <= 50 || frequenciaCardiaca >= 110){
                  chamarAjuda();
                }
                frequenciaCardiaca += 10; // Simula aumentar os batimentos cardíacos em 10
            }
        }, 0, 60000); // 60000 milissegundos = 1 minuto - Timer de atualização
    }

    public String atualizarLocalizacao() {
        // Simulação da obtenção da localização do GPS
        latitude = Math.random() * 90;
        longitude = Math.random() * 180;
        return "Latitude: " + latitude + ", Longitude: " + longitude;
        
    }

    public String medirBatimentosCardiacos() {
        // Verifica o nível de BPM      
        if (frequenciaCardiaca >= 110) { // Valor de batimentos acelerados (pode variar)
            return "BATIMENTOS CARDÍACOS ELEVADOS!";
                      
        }else if(frequenciaCardiaca <= 50){ // Valor de batimentos baixos (pode variar)
            return "BATIMENTOS CARDÍACOS BAIXOS!";
            
        }else{
            return null;  
        }
    }

    public void chamarAjuda() {
        // Simulação da chamada para o número "110"
        String nivelBatimentos = medirBatimentosCardiacos();
        JOptionPane.showMessageDialog(null, "Chamando Ajuda para " + nomePaciente, nivelBatimentos, JOptionPane.WARNING_MESSAGE);
    }

    public void enviarDadosParaServidor() {
        // Simulação do envio de dados para os responsáveis
        System.out.println("Dados enviados para os Médicos.");
    }

    private void exibirMensagem(String titulo, String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
        SmartWatchApp app = new SmartWatchApp();
        
    }
}
