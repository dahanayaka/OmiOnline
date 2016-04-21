import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;


public class Omi{

	public HttpServletResponse[] players = new HttpServletResponse[4];

	int []p1 = new int[13];
	int []p2 = new int[13];
	int []p3 = new int[13];
	int []p4 = new int[13];

	int trumph;

	private final HashMap<String, Integer> cardsMapToNumber = new HashMap<String, Integer>();
	private final HashMap<Integer, String> cardsMapTostring = new HashMap< Integer, String>();

    public Omi(){
        this.initPack();
    }

	public synchronized void addPlayer(HttpServletResponse player, int plyernmber){
		players[plyernmber] = player;
	}
    /**putting hashmaps, card name with id number
     * diamods - 1
     * clubs - 2
     * hearts - 3
     * spades - 4
     */

    public void initPack(){

        for(int i =0; i<4; i++){
            for(int j = 1; j<14; j++){
				cardsMapTostring.put(((i+1)*100 + j), (i+"_"+j+".png"));
				cardsMapToNumber.put((i+"_"+j+".png"), ((i+1)*100 + j));
            }
        }
    }

	public static void main(String [] argv){
		Omi g1 = new Omi();
		Integer []pack = g1.shuffle();
		g1.deal(pack);	
	}
	

	public Integer[] shuffle(){
		int []pack = {101,102,103,104,105,106,107,108,109,110,111,112,113,
				      201,202,203,204,205,206,207,208,209,210,211,212,213,
					  301,302,303,304,305,306,307,308,309,310,311,312,313,
					  401,402,403,404,405,406,407,408,409,410,411,412,413};

		ArrayList <Integer> obj = new ArrayList<Integer>();
		for(int i=0; i<52; i++){
			obj.add(pack[i]);
		}

		Collections.shuffle(obj);
		Integer [] ans = obj.toArray(new Integer[obj.size()]);
		return ans;
	}

	public void deal(Integer[] pack){
		for (int i=0;i<52;i+=4){
			p1[i] = pack[i];
			p2[i] = pack[i+1];
			p3[i] = pack[i+2];
			p4[i] = pack[i+3];
		}

		trumph = pack[51]/100;

		System.out.println("Player 1's hand:-");
		for(int i=0;i<13;i++){
			System.out.printf("%d ",p1[i]);
		}
		System.out.printf("\n\n");
		System.out.println("Player 2's hand:-");
		for(int i=0;i<13;i++){
			System.out.printf("%d ",p2[i]);
		}
		System.out.printf("\n\n");
		System.out.println("Player 3's hand:-");
		for(int i=0;i<13;i++){
			System.out.printf("%d ",p3[i]);
		}
		System.out.printf("\n\n");
		System.out.println("Player 4's hand:-");
		for(int i=0;i<13;i++){
			System.out.printf("%d ",p4[i]);
		}
		System.out.printf("\n\n");

		System.out.printf("Trumph is %d\n",trumph);
	}

}
