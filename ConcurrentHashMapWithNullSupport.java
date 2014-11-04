import java.io.Serializable;

public class ConcurrentHashMapWithNullSupport<K, V> extends ForwardingConcurrentMap<K, V> implements Serializable
{
	private static final long serialVersionUID = 1L;
	private ConcurrentMap<K,V> m_delegate = new ConcurrentHashMap<K, V>();
	private final K nullKey;
	private final V nullValue;

	public ConcurrentHashMapWithNullSupport(K nullKey, V nullValue)
	{
		this.nullKey = nullKey;
		this.nullValue = nullValue;
	}
	@SuppressWarnings("unchecked")
	public ConcurrentHashMapWithNullSupport()
	{
		this.nullKey = (K) "nullKey";
		this.nullValue = (V) "nullValue";
	}
	
	@Override
	public V get(Object key) 
	{
		V value;
		if (null == key)
		{
			value = super.get(nullKey);
		}
		else
		{
			value = super.get(key);
		}
		if (value == nullValue)
		{
			return null;
		}
		return value;
	}
	
	@Override
	public V put(K key, V value) 
	{
		K updatedKey = key;
		V updatedValue = value;
		if (null == updatedKey)
		{
			updatedKey = nullKey;
		}
		if (null == updatedValue)
		{
			updatedValue = nullValue;
		}
		return super.put(updatedKey, updatedValue);
	}
	
	@Override
	public V remove(Object key)
	{
		V $ = null;
		if (null == key)
		{
			$ = super.remove(nullKey);
		}
		else
		{
			$ = super.remove(key);
		}
		if (nullValue == $)
		{
			return null;
		}
		return $;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean remove(Object key, Object value)
	{
		K updatedKey = (K)key;
		V updatedValue = (V)value;
		if (null == updatedKey)
		{
			updatedKey = nullKey;
		}
		if (null == updatedValue)
		{
			updatedValue = nullValue;
		}
		return super.remove(updatedKey, updatedValue);
	}
	
	@Override
	public boolean containsKey(Object key)
	{
		if (null == key)
		{
			return super.containsKey(nullKey);
		}
		return super.containsKey(key);
	}
	
	@Override
	public void putAll(Map<? extends K, ? extends V> map)
	{
		for (Entry<? extends K, ? extends V> e : map.entrySet())
		{
			put(e.getKey(), e.getValue());
		}
	}		
	
