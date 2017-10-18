package com.compomics.secretesite.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * class representing proteins in the database
 * Created by davy on 5/10/2017.
 */

@Data
@Entity
@EqualsAndHashCode(exclude = {"domainsContainedInProtein","parentTranscripts"})
@ToString(exclude = {"domainsContainedInProtein","parentTranscripts"})
public class Protein {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "protein_id")
    private Integer proteinId;

    /**
     * The uniprot id of a protein
     */
    private String proteinAccession;
    private String proteinEnsemblAccession;
    private String proteinName;
    private String proteinLabel;

    @OneToMany(mappedBy = "protein",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<ProteinDomain> domainsContainedInProtein = new HashSet<>();


    @OneToMany(mappedBy = "proteinProduct",cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<TranscriptProtein> parentTranscripts = new HashSet<>();
}
