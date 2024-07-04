
# Development Guidelines

## Branch Management Conventions

### Branch Types

1. **Main Branch**: The primary branch where the source code of HEAD always reflects a production-ready state.
   - Name: `main`

2. **Development Branch**: Used to integrate features and fixes before they are ready for production.
   - Name: `develop`

3. **Feature Branches**: Used to develop new features for the upcoming or a distant future release.
   - Naming Convention: `feature/<feature-name>`
   - Example: `feature/user-registration`

### Branching Strategy

1. **Creating Branches**: Always branch off from the `develop` branch.
   ```bash
   git checkout develop
   git pull origin develop
   git checkout -b feature/<feature-name>
   ```

2. **Working on the feature**: Commit changes often with clear and concise messages.
   ```bash
   git add .
   git commit -m "Feature: Add user registration form"
   git push origin feature/<feature-name>
   ```

3. **Creating a pull request**: Go to the repository on GitHub.
   - Create a new pull request from `feature/<feature-name>` to `develop`.
   - Request reviews from team members.

4. **Review and merge into develop**: Address any review comments. Merge the pull request once approved.
   ```bash
   git checkout develop
   git pull origin develop
   git merge feature/<feature-name>
   ```

5. **Merging develop into main**: After thoroughly testing and validating the development branch, merge it into the main branch.
   ```bash
   git checkout main
   git pull origin main
   git merge develop
   ```

6. **Branch Deletion**: After a branch has been merged, it should be deleted to keep the repository clean.
   ```bash
   git branch -d feature/<feature-name>
   git push origin --delete feature/<feature-name>
   ```

### Commit Messages

- **Format**: Use clear and concise messages.
  - Structure: `[Type]: Description`
  - Types: `Feature`, `Fix`, `Refactor`, `Docs`, `Style`, `Test`, `Chore`
  - Example: `Feature: Add user registration`
- **Detail**: Provide enough detail to understand the changes without looking at the code.
- **Frequency**: Commit often with small, incremental changes to make reviews easier and maintain a clear history.


