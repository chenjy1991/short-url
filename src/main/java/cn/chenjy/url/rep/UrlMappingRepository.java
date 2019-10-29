package cn.chenjy.url.rep;

import cn.chenjy.url.entity.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlMappingRepository extends JpaRepository<UrlMapping,Long> {

    UrlMapping findByShortUrlCode(String shortUrlCode);
}
