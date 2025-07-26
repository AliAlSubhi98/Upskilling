# Cherry-Pick Practice Project

## Purpose
IntelliJ-ready Java project for practicing Git Level 2 cherry-pick skills:
- Selective commit picking between branches
- Advanced branching workflows
- Feature branch management

## Project Structure
```
cherry-pick-practice/
├── src/main/java/com/upskilling/cherrypick/
│   ├── Feature.java
│   └── FeatureApp.java
├── .gitignore
├── README.md
└── run.sh
```

## How to Use

### Option 1: IntelliJ IDEA
1. Open IntelliJ IDEA
2. File → Open → Select `cherry-pick-practice` folder
3. IntelliJ will automatically recognize the Maven-style structure
4. Run `FeatureApp.main()` from the editor

### Option 2: Command Line
```bash
./run.sh
```

## Git Practice Exercises
1. Initialize Git repository: `git init`
2. Create feature branches for new functionality
3. Make commits on different branches
4. Practice cherry-pick to move specific commits
5. Use IntelliJ Git integration for visual branch management 