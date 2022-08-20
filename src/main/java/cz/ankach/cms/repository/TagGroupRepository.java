package cz.ankach.cms.repository;

import cz.ankach.cms.entity.TagGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagGroupRepository extends JpaRepository<TagGroup, Long> {

}
