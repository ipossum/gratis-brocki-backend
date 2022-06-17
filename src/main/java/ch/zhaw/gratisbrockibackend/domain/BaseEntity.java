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

    @Column(nullable = false, updatable = false)
    protected Date createdDate;

    @Column(nullable = false, updatable = false, length = 50)
    protected String createdBy;

    @Column
    protected Date lastModifiedDate;

    @Column(length = 50)
    protected String lastModifiedBy;

    @Column
    protected String archivedDate;

    public BaseEntity () {}

    public BaseEntity (String createdBy) {
        this.createdDate = Date.from(Instant.now());
        this.createdBy = createdBy;
        this.lastModifiedDate = null;
        this.lastModifiedBy = null;
        this.archivedDate = null;
    }

}

