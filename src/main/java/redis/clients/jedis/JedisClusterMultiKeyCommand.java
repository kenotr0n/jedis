package redis.clients.jedis;

import redis.clients.jedis.exceptions.JedisAskDataException;
import redis.clients.jedis.exceptions.JedisClusterException;
import redis.clients.jedis.exceptions.JedisClusterMaxRedirectionsException;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.jedis.exceptions.JedisMovedDataException;
import redis.clients.jedis.exceptions.JedisRedirectionException;
import redis.clients.util.JedisClusterCRC16;

public abstract class JedisClusterMultiKeyCommand<T> extends JedisClusterCommand<T> {

  public JedisClusterMultiKeyCommand(JedisClusterConnectionHandler connectionHandler, int maxRedirections) {
    super(connectionHandler, maxRedirections);
  }

  public abstract T execute(Jedis connection);

  public T run(String... keys) {
      if (keys == null || keys.length == 0) {
        throw new JedisClusterException("No way to dispatch this command to Redis Cluster (no keys).");
      }

      return super.runWithRetries(this.redirections, false, false, keys);
  }

}
