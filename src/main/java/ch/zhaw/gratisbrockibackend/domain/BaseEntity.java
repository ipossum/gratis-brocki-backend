package ch.zhaw.gratisbrockibackend.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.OffsetDateTime;

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
    protected OffsetDateTime createdDate;

    @Column
    protected OffsetDateTime lastModifiedDate;

    @Column
    protected OffsetDateTime archivedDate;

    public BaseEntity () {
        this.createdDate = OffsetDateTime.now();
        this.lastModifiedDate = null;
        this.archivedDate = null;
    }

}

