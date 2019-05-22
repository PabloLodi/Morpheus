import java.io.*;
//inicio da classe Leitor
class Leitor extends Tools
{
	private String arquivo; //Lê o arquivo para ser Interpretado

	private LePrimitivos lePrimitivos = new LePrimitivos();
	
	public static boolean laco = true; //enquanto tudo estiver ocorrendo de maneira prevista
					//o laco de repeticao continua trocando de linha
	//Construtor
	public Leitor(String arq)
	{
		if(arq.contains(".mp"))
		{
			this.setArquivo(arq);
		}
		else
		{
			System.out.println("Para interpretar um código é preciso colocar na extesão .mp");
		}
	}

	public Leitor()
	{
	}

	//inicio do método reader
	public void reader() 
	{
		try
		{
			BufferedReader buffRead = new BufferedReader(new FileReader (this.getArquivo()));

            String linha = "";

            while(laco)
            {
			
				linha = buffRead.readLine();
                        
				if(linha != null)
                {
					if((linha.length() != 0 ) && (!linha.contains("//")) )
					{
						linha = linha.trim();
						lePrimitivos.idLinha(linha,0); //chama o metodo para ler as variaveis primitivas	
					}	

					else if( !tiraEspacos(linha).contains("{") && !tiraEspacos(linha).contains("}") && (!linha.contains("//")) && tiraEspacos(linha).length() != 0 ) 
					{
						
						ErrosNaCompilacao.getLineError(0);
						laco = false;
					}
					
					ErrosNaCompilacao.numeroDaLinha+=1;
                		
				}	
				else 
				{
					break;
				}

			}

			buffRead.close();
		}
		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	//fim do método reader
	
	//inicio do método getArquivo
	public String getArquivo()
	{
		return this.arquivo;
	}
	//fim do método getArquivo
	
	//inicio do método setArquivo
	public void setArquivo(String a)
	{
		this.arquivo = a;	
	}
	//fim do método setArquivo
}
//fim da classe Leitor
