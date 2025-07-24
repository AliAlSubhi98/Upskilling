
# 🚀 GitHub Pages Deployment Guide using MkDocs

This guide walks you through:
1. Initial setup
2. Deploying to GitHub Pages
3. Updating content
4. Best practices

---

## ✅ Prerequisites

- Python ≥ 3.7 installed
- Git installed and configured
- A GitHub repository (public or GitHub Pro if private)
- MkDocs and Material theme installed

Install requirements:

```bash
pip install mkdocs mkdocs-material
```

Optional plugins (recommended):

```bash
pip install mkdocs-git-revision-date-localized
```

---

## 🏗️ 1. Project Structure

Your repo structure should look like this:

```
Upskilling/
├── docs/
│   ├── index.md
│   ├── topics/
│   │   ├── Git-GitHub.md
│   │   ├── CI-CD.md
├── mkdocs.yml
```

---

## ⚙️ 2. `mkdocs.yml` Example

```yaml
site_name: Upskilling Journey
site_description: My curated docs for backend, DevOps, system design & beyond.
site_url: https://AliAlSubhi98.github.io/Upskilling/
repo_url: https://github.com/AliAlSubhi98/Upskilling
repo_name: GitHub

theme:
  name: material
  features:
    - navigation.sections
    - navigation.indexes
    - content.code.copy
    - content.action.edit
    - search.highlight
    - search.share
  palette:
    - scheme: default
      primary: blue
      accent: deep purple
    - scheme: slate
      primary: blue
      accent: deep purple
      toggle:
        icon: material/weather-night
        name: Switch to dark mode
  icon:
    repo: fontawesome/brands/github

nav:
  - Home: index.md
  - Git & GitHub: topics/Git-GitHub.md
  - CI/CD: topics/CI-CD.md
  - Deployment Guide: Deployment.md

markdown_extensions:
  - toc:
      permalink: true
  - admonition
  - codehilite
  - pymdownx.superfences

plugins:
  - search
  # Optional if installed
  # - git-revision-date-localized:
  #     fallback_to_build_date: true
```

---

## 🚀 3. Initial Deployment to GitHub Pages

### Step A: Initialize and Push Repo

```bash
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/AliAlSubhi98/Upskilling.git
git push -u origin main
```

### Step B: Build the Site

```bash
mkdocs build
```

### Step C: Deploy to GitHub Pages

```bash
mkdocs gh-deploy
```

You will be prompted for GitHub credentials. Use a [personal access token (PAT)](https://github.com/settings/tokens) instead of password.

---

## 🌐 4. Enable GitHub Pages in Repository

Go to:
`https://github.com/AliAlSubhi98/Upskilling/settings/pages`

- **Source**: Select `gh-pages` branch
- **Directory**: `/ (root)`
- Save ✅

---

## ✏️ 5. How to Update the Docs

1. Edit or add Markdown files under `docs/`
2. Run:

```bash
mkdocs build
mkdocs gh-deploy
```

3. Changes will appear live at the GitHub Pages URL.

---

## 🧼 Optional Cleanup

```bash
mkdocs build --clean
mkdocs serve  # for local preview
```

---

## 🛡️ Best Practices

- Commit changes regularly
- Use clear folder structure (`docs/topics/...`)
- Don't push `site/`
- Add `site/` to `.gitignore`
- Store secrets in `.env` and ignore them

