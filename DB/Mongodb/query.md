## Page

```
Pageable pageableRequest = PageRequest.of(0, 1);
Page<User> page = userRepository.findAll(pageableRequest);
```

## Sort

```
userRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
```

