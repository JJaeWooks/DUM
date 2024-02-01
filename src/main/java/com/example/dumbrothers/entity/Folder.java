package com.example.dumbrothers.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter

public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folderId;
    @Column
    private String folderName;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member user_Id;
    @Column
    private Long urlCounter;

    public void patch(Folder folder) {
        if(folder.folderName !=null){
            this.folderName=folder.folderName;
        }
    }
}
