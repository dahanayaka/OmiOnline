import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by e12283 on 4/18/16.
 */
public class omiOnline extends HttpServlet {

    public static HashMap<Integer, Omi> games = new HashMap<Integer, Omi>();
    public static int nextGame = 1 , nextPlayer = 0;
    public Omi game = null;

    public void doget(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");

        PrintWriter writer = response.getWriter();

        Cookie ckid = new Cookie("game", Integer.toString(nextGame));
        Cookie ckPlayer = new Cookie("player", Integer.toString(nextPlayer));
        synchronized (games) {
            if (games.containsKey(nextGame)) game = games.get(nextGame);
            else game = new Omi();
        }
        String reply;

        if((nextPlayer+1)>3) {
            reply = "{\"cards\":[],\"showHand\" : false, \"showCards\" : false , \"message\" : \"Waiting for others to connect. Only" + nextPlayer + " players connected ..\"}";

            writer.write(reply);
            writer.flush();

            }
        else {

            reply = "{\"cards\":[],\"showHand\" : false, \"showCards\" : false , \"message\" : \"Waiting for others to connect. Only" + nextPlayer + " players connected ..\"}";

            writer.write(reply);
            writer.flush();


        }

        for (int i = 0; i < 4 && game.players[i] != null; i++) {
            HttpServletResponse temp = game.players[i];
            writer = temp.getWriter();
            writer.write(reply);
            writer.flush();
        }

        game.addPlayer(response, nextPlayer);

        nextPlayer++;
        if(nextPlayer>3){
            nextPlayer = 0;
            nextGame++;
        }


    }

}


