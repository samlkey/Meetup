import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static spark.Spark.*;


public class API {
    public static Map<Integer, String> code = new HashMap<Integer, String>() {{
        put(101, "[ERROR] UNABLE TO CONNECT TO DATABASE SERVER");
        put(102, "[PASS] SUCCESSFULLY CONNECTED TO DATABASE SERVER");
    }};
    private static final int PORT = 6666;
    private static boolean exit;
    private static Client c;

    public static void main(String[] args) throws IOException {
        System.out.println("[SERVER] STARTING SERVER");
        System.out.println("[SERVER] STARTING API ROUTES");
        //get("/hello", (req, res) -> "Hello World");
        post("/userReg", API::RegisterUser);
        post("/userLogin", API::LoginUser);
        System.out.println("[SERVER] CONNECTING TO DATABASE SERVER");
        c = new Client();
        c.startConnection("127.0.0.1", PORT);




        System.out.println("[SERVER] API READY");
    }

    public static String RegisterUser(spark.Request req, spark.Response res) throws IOException {
        System.out.println(req.body());
        String msg = req.body().replaceAll("\\s+","");
        c.sendMessage("REGUSER!" + msg);
        System.out.println("[SERVER] USER REGISTERED");
        return "[REGISTERING USER]";
    }

    public static String LoginUser(spark.Request req, spark.Response res){
        return "";
    }


}

