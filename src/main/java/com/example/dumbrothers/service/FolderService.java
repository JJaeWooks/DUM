package com.example.dumbrothers.service;

import com.example.dumbrothers.dto.FolderDto;
import com.example.dumbrothers.entity.Link;
import com.example.dumbrothers.entity.Folder;
import com.example.dumbrothers.repository.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FolderService {

    @Autowired
    private FolderRepository folderRepository;

    private DumService dumService;

    public FolderService(DumService dumService){
        this.dumService = dumService;
    }

    public List<Folder> show() {
        return folderRepository.findAll();
    }

    //showFolderWithLingCount
    public List<Folder> showFolderCount(List<Folder> folders) {
        List<Folder> folderDTOs = new ArrayList<>();

        for (Folder folder : folders) {
            List<Link> links = dumService.inshow(folder.getFolderId());
            Long linkCount = (long) links.size();
            Folder folderDTO = new Folder(folder.getFolderId(), folder.getFolderName(), folder.getUser_Id(), linkCount);
            folderDTOs.add(folderDTO);
        }
        return folderDTOs;
    }

    public Folder save(FolderDto folderdto) {
        Folder folder = folderdto.toEntity();
        Folder foldercreate=folderRepository.save(folder);
        return foldercreate;
    }

    public Folder update(Long id, FolderDto dto) {
        Folder folder=dto.toEntity();
        Folder target=folderRepository.findById(id).orElse(null);

        if (target==null || id.equals(folder.getFolderId())){
            return null;
        }

        target.patch(folder);
        Folder updated=folderRepository.save(target);
        return updated;
    }
}
