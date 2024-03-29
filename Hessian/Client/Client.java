import com.caucho.hessian.client.HessianProxyFactory;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ������� on 19.06.2015.
 */
public class Client {

    public static List<Integer> list;
    public static AccountService accountService;
    public static int readers;
    public static int writers;

    private static void readAttributes(String[] args) throws Exception{
        try {
            //Read properties from file...
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("attributes.txt")));
            bufferedReader.lines().forEach(line -> setAttributes(line));
            //Bad. Ok. Let's try to read from arguments
            if(list == null)
                throw new FileNotFoundException();
        } catch (FileNotFoundException e) {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                s.append(args[i] + " ");
            }
           setAttributes(s.toString());
        }
        finally {
            if(list == null) {
                throw new Exception("id List is null");
            }
        }
    }

    private static void setAttributes(String line) {
        StringTokenizer stringTokenizer = new StringTokenizer(line);
        while (stringTokenizer.hasMoreTokens()) {
            String token = stringTokenizer.nextToken();
            if (token.equals("-rCount")) {
                readers = Integer.parseInt(stringTokenizer.nextToken());
            }
            if (token.equals("-wCount")) {
                writers = Integer.parseInt(stringTokenizer.nextToken());
            }
            if (token.equals("-idList")) {
                StringTokenizer tokenizer = new StringTokenizer(stringTokenizer.nextToken());
                list = new ArrayList<Integer>(tokenizer.countTokens());
                while (tokenizer.hasMoreTokens()) {
                    list.add(Integer.parseInt(tokenizer.nextToken(",")));
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        readAttributes(args);
        HessianProxyFactory factory =new HessianProxyFactory();
        //Create a remote access
        accountService = (AccountService) factory.create(AccountService.class,"http://localhost:6969/service");
        for (int i = 0; i < readers; i++) {
            new GetDDOS().start();
        }
        for (int i = 0; i < writers; i++) {
            new AddDDOS().start();
        }
    }
}
