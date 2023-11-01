package org.tcpx.sharine.service;


import org.springframework.data.domain.Sort;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.GeoResults;
import org.springframework.data.geo.Point;
import org.springframework.data.redis.connection.BitFieldSubCommands;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * redis操作
 *
 * @author yezhqiu
 * @date 2021/06/13
 * @since 1.0.0
 **/
@Service
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public Boolean del(String key) {
        return redisTemplate.delete(key);
    }

    public Long del(List<String> keys) {
        return redisTemplate.delete(keys);
    }

    public Boolean expire(String key, long time) {
        return redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Long incr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    public Long decr(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    public Object hGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public Boolean hSet(String key, String hashKey, Object value, long time) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        return expire(key, time);
    }

    public void hSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public Map hGetAll(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Boolean hSetAll(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        return expire(key, time);
    }

    public void hSetAll(String key, Map<String, ?> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public void hDel(String key, Object... hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    public Boolean hHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public Long hIncr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, delta);
    }

    public Long hDecr(String key, String hashKey, Long delta) {
        return redisTemplate.opsForHash().increment(key, hashKey, -delta);
    }

    public Double zIncr(String key, Object value, Double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, score);
    }

    public Double zDecr(String key, Object value, Double score) {
        return redisTemplate.opsForZSet().incrementScore(key, value, -score);
    }

    public Map<Object, Double> zReverseRangeWithScore(String key, long start, long end) {
        return redisTemplate.opsForZSet().reverseRangeWithScores(key, start, end)
                .stream()
                .collect(Collectors.toMap(ZSetOperations.TypedTuple::getValue, ZSetOperations.TypedTuple::getScore));
    }

    public Long zSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    public Double zScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    public Map<Object, Double> zAllScore(String key) {
        return Objects.requireNonNull(redisTemplate.opsForZSet().rangeWithScores(key, 0, -1))
                .stream()
                .collect(Collectors.toMap(ZSetOperations.TypedTuple::getValue, ZSetOperations.TypedTuple::getScore));
    }

    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    public Long sAddExpire(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    @Nullable
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    public Long lPush(String key, Object value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    public Long lPush(String key, Object value, long time) {
        Long index = redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
        return index;
    }

    public Long lPushAll(String key, Object... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    public Long lPushAll(String key, Long time, Object... values) {
        Long count = redisTemplate.opsForList().rightPushAll(key, values);
        expire(key, time);
        return count;
    }
    public Object lRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }
    public Long lLeftPush(String key, Object value) {
        return redisTemplate.opsForList().leftPush(key,value);
    }
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    public Boolean bitAdd(String key, int offset, boolean b) {
        return redisTemplate.opsForValue().setBit(key, offset, b);
    }

    public Boolean bitGet(String key, int offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    public Long bitCount(String key) {
        return redisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }

    public List<Long> bitField(String key, int limit, int offset) {
        return redisTemplate.execute((RedisCallback<List<Long>>) con ->
                con.bitField(key.getBytes(),
                        BitFieldSubCommands.create().get(BitFieldSubCommands.BitFieldType.unsigned(limit)).valueAt(offset)));
    }

    public byte[] bitGetAll(String key) {
        return redisTemplate.execute((RedisCallback<byte[]>) con -> con.get(key.getBytes()));
    }

    public Long hyperAdd(String key, Object... value) {
        return redisTemplate.opsForHyperLogLog().add(key, value);
    }

    public Long hyperGet(String... key) {
        return redisTemplate.opsForHyperLogLog().size(key);
    }

    public void hyperDel(String key) {
        redisTemplate.opsForHyperLogLog().delete(key);
    }

    public Long geoAdd(String key, Double x, Double y, String name) {
        return redisTemplate.opsForGeo().add(key, new Point(x, y), name);
    }

    public List<Point> geoGetPointList(String key, Object... place) {
        return redisTemplate.opsForGeo().position(key, place);
    }

    public Distance geoCalculationDistance(String key, String placeOne, String placeTow) {
        return redisTemplate.opsForGeo()
                .distance(key, placeOne, placeTow, RedisGeoCommands.DistanceUnit.KILOMETERS);
    }

    public GeoResults<RedisGeoCommands.GeoLocation<Object>> geoNearByPlace(String key, String place, Distance distance, long limit, Sort.Direction sort) {
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().includeCoordinates();
        // 判断排序方式
        if (Sort.Direction.ASC == sort) {
            args.sortAscending();
        } else {
            args.sortDescending();
        }
        args.limit(limit);
        return redisTemplate.opsForGeo()
                .radius(key, place, distance, args);
    }

    public List<String> geoGetHash(String key, String... place) {
        return redisTemplate.opsForGeo()
                .hash(key, place);
    }

    public void zRem(String key, Object value) {
        redisTemplate.opsForZSet().remove(key, value);
    }
}
