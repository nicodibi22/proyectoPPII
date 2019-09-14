package proyecto.servicios.impl;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import proyecto.Token;


public class GeneradorToken {

	public static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static final int SECURE_TOKEN_LENGTH = 256;
	//private static final SecureRandom random = new SecureRandom();
	//private static final char[] symbols = CHARACTERS.toCharArray();
	//private static final char[] buf = new char[SECURE_TOKEN_LENGTH];
	private static final String SECRET_KEY = "rz8LuOtFBXphj9WQfvFh";     
    private static final String JWT_HEADER = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
    private JSONObject payload = new JSONObject();
    private String signature;
    private String encodedHeader;
	
    private String token;
    
	/**
	 * Generate the next secure random token in the series.
	 */
	/*public static String nextToken() {
	    for (int idx = 0; idx < buf.length; ++idx)
	        buf[idx] = symbols[random.nextInt(symbols.length)];
	    return new String(buf);
	}*/
	
	private static final String _alg = "HmacSHA256";
	private final String _salt = "rz8LuOtFBXphj9WQfvFh"; // Generated at https://www.random.org/strings
	
	
	private GeneradorToken() {
		
        encodedHeader = TokenUtiles.encode(JWT_HEADER);
		
    }

    public GeneradorToken(JSONObject payload) {
        //this(payload.getString("sub"), payload.getJSONArray("aud"), payload.getLong("exp"));
    }

    public GeneradorToken(String usuario, long expires) {
        this();
        payload.put("usuario", usuario);
        payload.put("exp", expires);
        payload.put("iat", LocalDateTime.now().toEpochSecond(ZoneOffset.UTC));
                
        signature = hmacSha256(encodedHeader + "." + TokenUtiles.encode(payload.toJSONString()), SECRET_KEY);
        token = encodedHeader + "/" + TokenUtiles.encode(payload.toJSONString()) + "/" + signature;
    }
	
    public static String generarSignature(String header, String body) {
    	return hmacSha256(header + "." + body, SECRET_KEY);
    }
    
    public Token getToken() {
    	Token tokenObject = new Token(token);
    	return tokenObject;
    }
    
    public static String hmacSha256(String data, String secret) {
        try {

            //MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = secret.getBytes(StandardCharsets.UTF_8);//digest.digest(secret.getBytes(StandardCharsets.UTF_8));

            Mac sha256Hmac = Mac.getInstance(_alg);
            SecretKeySpec secretKey = new SecretKeySpec(hash, _alg);
            sha256Hmac.init(secretKey);

            byte[] signedBytes = sha256Hmac.doFinal(data.getBytes(StandardCharsets.UTF_8));
            return TokenUtiles.encode(signedBytes);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            
            return null;
        }
    }

    
    public static boolean verificarSignature(String token) {
    	String[] tokenSplit = token.split("/");
		
		for (String ts : tokenSplit) {
			System.out.println(TokenUtiles.decode(ts));
		}
		return true;
    }
    
    public static long getExpires(String token) {
    	String[] tokenSplit = token.split("/");
		
		String json = TokenUtiles.decode(tokenSplit[1]);

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = null;
		try {
			jsonObject = (JSONObject)parser.parse(json);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Long.parseLong(jsonObject.get("exp").toString());
    }
}
