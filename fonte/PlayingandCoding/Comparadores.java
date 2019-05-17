import java.util.HashMap;

//inicio da classe Comparadores
public class Comparadores extends Tools
{
	
	public static HashMap<String,String> tipoVariaveis = new HashMap<String,String>(); //armazena os tipos das variaveis
	
	public Int ints = new Int();
	
	//inicio do método getVariaveis
	public void getVariaveis( String line )
	{
		String v1 = "", v2 = "", cleanLine = tiraEspacos(line);
		
		int pos=0;

		for(int i = 0; i < cleanLine.length(); i++)
		{
			if( cleanLine.charAt(i) == '=')
			{
				pos = (i+1);
				for(int ii = i+1; ii< cleanLine.length()-1; ii++)
				{
					v2=v2+""+cleanLine.charAt(ii);
				}//laço que pega o nome da variavel dps do =
				break;
			}
			else
			{
				v1=v1+""+cleanLine.charAt(i);
				
			}//nome antes do igual
		}
		this.trocaDeVariaveis(v1,v2,cleanLine,pos); //troca o valor da variavel
	}
	//fim do método getVariaveis
	
    // Copiei na cara dura e nem chamei a função igual gente
    private String valorDoVetor(String value)
	{
		Int inteiro = new Int();
		
		String nomeVariavel = value.substring(value.indexOf("]")+1,value.length()); //pega o nome da variavel
		
		int index = inteiro.indetificadorDeNumerosInt(value.substring(value.indexOf("[")+1, value.indexOf("]")),0);
	
		if(Comparadores.tipoVariaveis.get(nomeVariavel).equals("int"))
		{
			return ""+Int.vetoresArmazenados.get(nomeVariavel).get(index);
		}

        else if(Comparadores.tipoVariaveis.get(nomeVariavel).equals("double"))
        {
            return ""+Doubles.vetoresArmazenados.get(nomeVariavel).get(index);
        }
		return "";
	}

    public boolean comparaVetor(String expressao)
	{
		String v1 = "", v2 = "", comparacao = ""; // inicializando
		
		for(int i = 0; i < expressao.length(); i++) 
		{
            if((expressao.charAt(i) == '='||expressao.charAt(i) == '>'||expressao.charAt(i) == '<'||expressao.charAt(i) == '!') && (expressao.charAt(i+1) == '=' ||expressao.charAt(i+1) == '<' ||expressao.charAt(i+1) == '>' ))
            {
                comparacao = expressao.substring(i,i+2);
                v1 = valorDoVetor(expressao.substring(0,i));
                v2 = valorDoVetor(expressao.substring(i+2,expressao.length()));
                if ( (v1.chars().allMatch(Character::isDigit)) || (v2.chars().allMatch(Character::isDigit)) ) 
                return valorBooleanoDaExpressao(comparacao,Double.parseDouble(v1),Double.parseDouble(v2));
                else return false;
            }
		}
        return false;
	}
	
	//inicio do método comparaChar
	public boolean comparaChar(String expressao)
	{
		char v1 = '-', v2 = '+', c1 = '*', c2 = '/' ; //so para inicializar
		
		for(int i = 0; i< expressao.length(); i++)
		{
			if(expressao.charAt(i) == '=' && expressao.charAt(i+1) == '=')
			{
                if( tipoVariaveis.containsKey(expressao.substring(0,i) ) 
                    && tipoVariaveis.get(expressao.substring(0,i)).equals("char"))
				{
					v1 = Char.variaveisArmazenadas.get(expressao.substring(0,i));
				}
                else                
                {
                    v1 = expressao.substring(0,1).charAt(0);
                }
                if( tipoVariaveis.containsKey(expressao.substring(i+2,expressao.length()) )
                    && tipoVariaveis.get(expressao.substring(i+2,expressao.length())).equals("char"))
				{
					v2 = Char.variaveisArmazenadas.get(expressao.substring(i+2,expressao.length()));
				}
                else                
                {
                    v2 = expressao.substring(i+2,expressao.length()).charAt(0);
                }



			}
		}
		return v1 == v2 ? true:false;
	}
	//fim do método comparaChar;

	// inicio do método comparaInt
	public boolean comparaInt(String expressao)
	{
		int v1 = 0, v2 = 0; //so para inicializar
		String comparacao ="";
		for(int i = 0; i< expressao.length(); i++)
		{
			if((expressao.charAt(i) == '='||expressao.charAt(i) == '>'||expressao.charAt(i) == '<'||expressao.charAt(i) == '!') && (expressao.charAt(i+1) == '=' ||expressao.charAt(i+1) == '<' ||expressao.charAt(i+1) == '>' ))
			{
				comparacao = expressao.substring(i,i+2);
                if( expressao.substring(0,i).chars().allMatch(Character::isDigit) ) 
                {
                    v1 = Integer.parseInt(expressao.substring(0,i));
                }
				else if(tipoVariaveis.get(expressao.substring(0,i)).equals("int"))
				{
					v1 = Int.variaveisArmazenadas.get(expressao.substring(0,i));
				}
                if( expressao.substring(i+2,expressao.length()).chars().allMatch(Character::isDigit) )
				{
					v2 = Integer.parseInt(expressao.substring(i+2));
				}
				else if(tipoVariaveis.get(expressao.substring(i+2,expressao.length())).equals("int"))
				{
					v2 = Int.variaveisArmazenadas.get(expressao.substring(i+2,expressao.length()));
				}
			}
		}
		return valorBooleanoDaExpressao(comparacao, v1, v2);
	}
	// fim do método comparaInt

	// inicio do método comparaDouble
	public boolean comparaDouble(String expressao)
	{
		double v1 = 0, v2 = 0; //so para inicializar
		
		String comparacao ="";
		
		for(int i = 0; i< expressao.length(); i++)
		{
			if((expressao.charAt(i) == '='||expressao.charAt(i) == '>'||expressao.charAt(i) == '<'||expressao.charAt(i) == '!') && expressao.charAt(i+1) == '=')
			{
				comparacao = expressao.substring(i,i+2);
				if(tipoVariaveis.get(expressao.substring(0,i)).equals("double"))
				{
					v1 = Doubles.variaveisArmazenadas.get(expressao.substring(0,i));
				}
                else if( expressao.substring(0,i).chars().allMatch(Character::isDigit) )
				{
					v1 = Double.parseDouble(expressao.substring(0,i));
				}
				if(tipoVariaveis.get(expressao.substring(i+2,expressao.length())).equals("double"))
				{
					v2 = Doubles.variaveisArmazenadas.get(expressao.substring(i+2,expressao.length()));
				}
                else if( expressao.substring(i+2,expressao.length()).chars().allMatch(Character::isDigit) )
				{
					v2 = Double.parseDouble(expressao.substring(i+2));
				}
			}
		}
		return valorBooleanoDaExpressao(comparacao,v1,v2);
	}
	// fim do método comparaDouble
	
	// inicio do método comparaStr 
	public boolean comparaStr(String expressao) //esse metodo vai ler o nome das variaveis usadas e vai comparar com o conteudo delas e retornar true se forem iguals 
	{
		String v1 = "",v2 = "";
		
		for(int i = 0; i< expressao.length(); i++)
		{
			if(expressao.charAt(i) == '=' && expressao.charAt(i+1) == '=')
			{
				if ( tipoVariaveis.containsKey(expressao.substring(0,i)) 
                    && tipoVariaveis.get(expressao.substring(0,i)).equals("string"))
                {
				    v1 = Strings.variaveisArmazenadas.get(expressao.substring(0,i));
    		    }
                else 
                {
                    v1 = expressao.substring(0,i);
                }
                
                if ( tipoVariaveis.containsKey( expressao.substring(i+2,expressao.length() ) ) 
                    && tipoVariaveis.get(expressao.substring(i+2,expressao.length())).equals("string") ) 
                {
				    v2 = Strings.variaveisArmazenadas.get(expressao.substring(i+2,expressao.length()));
                }
                else 
                {
                    v2 = expressao.substring(i+2,expressao.length());
                }
			}
		}
		return v1.equals(v2) ? true:false;
	}
	//fim do método comparaStr;

	//inicio do método trocaDeVariaveis
	public void trocaDeVariaveis( String v1, String v2,String line, int pos )
	{		
		if( tipoVariaveis.get(v1).equals("int"))
		{
				if (48<= v2.charAt(0) && v2.charAt(0) <= 57)
				{
					Int.variaveisArmazenadas.put(v1,ints.indetificadorDeNumerosInt(line,pos)); // se o primeiro caractere deposi do = for
														// um numero entre 0 e 9 ele reconhece como se fosse um numero a ser armazenado
				}
				
				else
				{
					Int.variaveisArmazenadas.put(v1,Int.variaveisArmazenadas.get(v2));// troca uma variavel pela outra
				}

		}

		else if( tipoVariaveis.get(v1).equals("double") && tipoVariaveis.get(v2).equals("double"))
		{
			Doubles.variaveisArmazenadas.put(v1,Doubles.variaveisArmazenadas.get(v2));

		}

		else if( tipoVariaveis.get(v1).equals("bool"))
		{
			if(v2.equals("false") || v2.equals("true"))
			{
				Bool.variaveisArmazenadas.put(v1,Boolean.valueOf(v2));
			}
			else if(tipoVariaveis.get(v2).equals("bool"))
			{
				Bool.variaveisArmazenadas.put(v1,Bool.variaveisArmazenadas.get(v2));
		
			}
		}

		else if( tipoVariaveis.get(v1).equals("char"))
		{
			if(v2.charAt(0) == 39 && v2.charAt(2)== 39)
			{
				Char.variaveisArmazenadas.put(v1,v2.charAt(1));
			}
			else if(tipoVariaveis.get(v2).equals("char"))
			{
				Char.variaveisArmazenadas.put(v1,Char.variaveisArmazenadas.get(v2));
			}
		}
		else if( tipoVariaveis.get(v1).equals("string"))
		{
			if(v2.charAt(0) == '"')
			{
				Strings.variaveisArmazenadas.put(v1, v2.substring(1, v2.length()-1));
			}
			else if (tipoVariaveis.get(v2).equals("string"))
			{
				Strings.variaveisArmazenadas.put(v1, Strings.variaveisArmazenadas.get(v2));
			}
		}
	}
	//fim do método trocaDeVariaveis

	//inicio do método valorBooleanoDaExpressao
	public boolean valorBooleanoDaExpressao(String comparacao,double v1, double v2)
	{
		if(comparacao.equals(">="))
		{
			return v1 >= v2 ? true:false;
		}

		else if(comparacao.equals(">>"))
		{
			return v1 > v2 ? true:false;
		}
		
		else if(comparacao.equals("<<"))
		{
			return v1 < v2 ? true:false;
		}

		else if(comparacao.equals("<="))
		{
			return v1 <= v2 ? true:false;
		}
		else if(comparacao.equals("=="))
		{
			return v1 == v2 ? true:false;
		}
		else if(comparacao.equals("!="))
		{
			return v1 == v2 ? false:true;
		}
		return false;
	}
	//fim do método valorBooleanoDaExpressao
}
//fim da classe Comparadores
