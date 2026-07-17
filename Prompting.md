# QA Mastering apps

These are prompts that helped build this app

## 1. Integration tests completion
```text
can you complete all integration tets for all sub-modules of library-books when possible? Make sure to cover all corner cases and make sure to only use mockk for mocking and kotest assertions..
```

## 2. Removal of excessive maven plugin usage
```text
can you please remove all maven-compiler-plugin declarations from all modules and sub-modules in this project? Keep only one at the top level as it is right now. All the other maven-compiler-plugin declarations can be removed
```
## 3. Removal of excessive maven-surefire-plugin and maven-failsafe-plugin (straight after the last one)

```text
can you do exactly the same, but in this case remove maven-surefire-plugin and maven-failsafe-plugin plugins? Remember to keep it in the parent
```
