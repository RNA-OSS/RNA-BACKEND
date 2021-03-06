package com.dna.rna.domain.schoolUser;

import com.dna.rna.domain.school.QSchool;
import com.dna.rna.domain.school.School;
import com.dna.rna.domain.user.QUser;
import com.dna.rna.domain.user.User;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class SchoolUserRepositoryImpl extends QuerydslRepositorySupport implements CustomSchoolUserRepository {

    @PersistenceContext
    EntityManager em;

    SchoolUserRepositoryImpl() {
        super(SchoolUser.class);
    }

    @Transactional
    public SchoolUser save(final SchoolUser schoolUser) throws DataIntegrityViolationException {
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchoolUser qSchoolUser = new QSchoolUser("su");
        List<SchoolUser> exist = queryFactory.selectFrom(qSchoolUser).where(
                qSchoolUser.school.id.eq(schoolUser.getUser().getId())
                .and(qSchoolUser.user.id.eq(schoolUser.getSchool().getId()))).fetch();
        if (exist.size() != 0) {
            throw new DataIntegrityViolationException(String.format("user id = [%d], school id = [%d] 인" +
                            "schoolUser 가 이미 존재합니다.",
                    schoolUser.getUser().getId(), schoolUser.getSchool().getId()));
        }
        em.persist(schoolUser);
        return schoolUser;
    }

    @Transactional
    public List<SchoolUser> findByLoginIdAndSchoolId(final String loginId, final long schoolId) {


        JPAQuery query = new JPAQuery(em);
        JPAQueryFactory queryFactory = new JPAQueryFactory(em);
        QSchoolUser qSchoolUser = new QSchoolUser("su");
        QSchool qSchool = new QSchool("s");
        QUser qUser = new QUser("u");
        School school = queryFactory.selectFrom(qSchool).where(qSchool.schoolName.eq("dongguk")).fetchOne();
        User user =  queryFactory.selectFrom(qUser).where(qUser.loginId.eq("4whomtbts")).fetchOne();
        //em.persist(user);
        SchoolUser schoolUser = SchoolUser.of(user, school, false);
        //em.persist(schoolUser);
        List<SchoolUser> schoolUsers =
                queryFactory.selectFrom(qSchoolUser)
                     .where(qSchoolUser.user.id.eq((long)4)
                            .and(qSchoolUser.school.id.eq((long)3))).fetch();

        //schoolUsers.forEach(System.out::println);
        System.out.println(schoolUsers.size());

        return schoolUsers;
    }
}
