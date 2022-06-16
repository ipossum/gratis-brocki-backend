package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    protected Date createdDate = Date.from(Instant.now());

    @CreatedBy
    @Column(name = "created_by", nullable = false, updatable = false, length = 50)
    protected String createdBy = null;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    protected Date lastModifiedDate = null;

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    protected String lastModifiedBy = null;

    @Column(name = "archived_date")
    protected String archivedDate = null;

}

