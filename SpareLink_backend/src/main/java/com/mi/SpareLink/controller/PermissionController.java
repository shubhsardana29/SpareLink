// package com.mi.SpareLink.controller;

// import com.mi.SpareLink.exception.ResourceNotFoundException;
// import com.mi.SpareLink.model.Permission;
// import com.mi.SpareLink.repository.PermissionRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/api/permissions")
// public class PermissionController {

//     @Autowired
//     private PermissionRepository permissionRepository;

//     @GetMapping
//     public List<Permission> getAllPermissions() {
//         return permissionRepository.findAll();
//     }

//     @PostMapping
//     public ResponseEntity<String> createPermission(@RequestBody Permission permission) {
//         permissionRepository.save(permission);
//         return ResponseEntity.ok("Permission created with ID: " + permission.getPermissionID());
//     }

//     @GetMapping("/{id}")
//     public ResponseEntity<Permission> getPermissionById(@PathVariable Long id) {
//         Permission permission = permissionRepository.findById(id)
//                 .orElseThrow(() -> new ResourceNotFoundException("Permission not found with ID: " + id));
//         return ResponseEntity.ok(permission);
//     }

//     @PutMapping("/{id}")
//     public ResponseEntity<String> updatePermission(@PathVariable Long id, @RequestBody Permission updatedPermission) {
//         if (!permissionRepository.existsById(id)) {
//             throw new ResourceNotFoundException("Permission not found with ID: " + id);
//         }
//         updatedPermission.setPermissionID(id); // Ensure the ID is set
//         permissionRepository.save(updatedPermission);
//         return ResponseEntity.ok("Permission updated with ID: " + id);
//     }

//     @DeleteMapping("/{id}")
//     public ResponseEntity<String> deletePermission(@PathVariable Long id) {
//         permissionRepository.deleteById(id);
//         return ResponseEntity.ok("Permission deleted with ID: " + id);
//     }
// }
