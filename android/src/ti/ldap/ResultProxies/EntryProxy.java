package ti.ldap.ResultProxies;

import java.util.Iterator;

import org.appcelerator.kroll.KrollProxy;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiBlob;

import com.unboundid.ldap.sdk.SearchResultEntry;
import com.unboundid.ldap.sdk.Attribute;


@Kroll.proxy
public class EntryProxy extends KrollProxy {
	
	private SearchResultEntry _entry;
	private Iterator<Attribute> _iterator = null;
	
	public EntryProxy(SearchResultEntry entry) {
		super();
		_entry = entry;
	}
	
	@Kroll.method
	public String getDn()
	{
		return _entry.getDN();
	}
	
	@Kroll.method
	public String firstAttribute()
	{
		_iterator = _entry.getAttributes().iterator();
		if (_iterator.hasNext()) {
			Attribute attribute = _iterator.next();
			return attribute.getName();
		}
		
		return null;
	}
	
	@Kroll.method
	public String nextAttribute()
	{
		if ((_iterator != null) && _iterator.hasNext()) {
			Attribute attribute = _iterator.next();
			return attribute.getName();
		}
		
		return null;
	}
	
	@Kroll.method
	public String[] getValues(String name)
	{
		String[] result = _entry.getAttributeValues(name);
		
		return result;
	}
	
	@Kroll.method
	public Object[] getValuesLen(String name)
	{
		Attribute attribute = _entry.getAttribute(name);
		if (attribute != null) {
			int count = attribute.size();
			Object[] result = new Object[count];
			byte[][] byteArray = attribute.getValueByteArrays();
			for (int i=0; i < count; i++) {
				result[i] = TiBlob.blobFromData(byteArray[i]);
			}
			return result;
		}
		
		return null;	
	}
}