package org.tcpx.sharine.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.tcpx.sharine.entity.VideoTag;

import java.util.List;

public interface VideoTagRepository extends JpaRepository<VideoTag, Long> {
    // JPA 方法映射异常，注释掉了
//    List<VideoTag> findAll(Example<VideoTag> example, PageRequest pageRequest);
}
