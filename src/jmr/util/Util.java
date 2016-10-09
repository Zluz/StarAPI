package jmr.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

public class Util {

	
	public static final String UNKNOWN = "(unknown)";

	final public static String UTF8 = StandardCharsets.UTF_8.name();
		
	
	public static File extract( final String strInnerFile ) {
		try {
			final File file = File.createTempFile( "temporary_", ".jar" );
			file.delete();
		    final Class<? extends String> clazz = strInnerFile.getClass();
			final InputStream link = clazz.getResourceAsStream( strInnerFile );
			if ( null==link ) {
				System.err.println( "Null stream, file: " + strInnerFile );
				return null;
			}
			Files.copy( link, file.getAbsoluteFile().toPath() );
			file.deleteOnExit();
			return file;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static String getHostname() {
		try {
			final InetAddress host = InetAddress.getLocalHost();
			final String strHostname = host.getHostName();
			return strHostname;
		} catch ( final UnknownHostException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UNKNOWN;
	}
	
	
	public static String getHostIP() {
		try {
			final InetAddress host = InetAddress.getLocalHost();
			final String strHostIP = host.getHostAddress();
			return strHostIP;
		} catch ( final UnknownHostException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return UNKNOWN;
	}
	
	
	public static String getTextFromFile( final File file ) {
		if ( null==file ) return null;
		if ( !file.exists() ) return null;
		
		try {
			final byte[] encoded;
			encoded = Files.readAllBytes( Paths.get( file.getAbsolutePath() ) );
			final String strText = new String( encoded, StandardCharsets.UTF_8 );
			return strText;
		} catch ( final IOException e ) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public static String truncate( 	final String strText,
									final int iLength ) {
		if ( null==strText ) return null;
		final String strNew = strText.substring( 0, 
				Math.min( strText.length(), iLength ) );
		return strNew;
	}
	


	public static Map<String,String> extractParameters( final String query ) {
	    final Map<String, String> map = new LinkedHashMap<String, String>();
	    if ( null!=query && !query.isEmpty() ) {
		    final String[] pairs = query.split( "&" );
		    for ( final String pair : pairs ) {
		        final int idx = pair.indexOf( "=" );
		        try {
					final String strKey = URLDecoder.decode( pair.substring(0, idx), UTF8 );
					final String strValue = URLDecoder.decode( pair.substring(idx + 1), UTF8 );
					map.put( strKey, strValue );
				} catch ( final UnsupportedEncodingException e ) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
	    }
	    return map;
	}
	
	
	
	public static void main( final String[] args ) {
//		final long lNow = (new Date()).getTime();
//		System.out.println( lNow );
	}
	
	
}
