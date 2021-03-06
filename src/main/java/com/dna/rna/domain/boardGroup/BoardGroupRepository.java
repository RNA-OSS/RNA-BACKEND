package com.dna.rna.domain.boardGroup;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardGroupRepository extends JpaRepository<BoardGroup, Long>, CustomBoardGroupRepository {

    public List<BoardGroup> findBoardGroupsByClubIdOrderByDisplayOrder(long clubId);

}

