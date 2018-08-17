# CREC

## I. Introduction

This project is built to automatically recommend clones for refactoring, which leverages both the present status and the past evolution of clones. 

* This project is implemented for the research paper “Automatic Clone Recommendation for Refactoring Based on the Present and the Past” accepted by [ICSME 2018](https://icsme2018.github.io/).*

## II. Environment

* OS: GNU Linux (Tested on Ubuntu 14.04 LTS) 
* JDK: JDK1.8

## III. How to run

In the runnable folder, runCREC.sh is an example shell. It contains follow steps:
* checking necessary software: to make sure you have installed git.
* git clone project repository from the Internet: you can replace here for your own need.
* get project commits: to generate all commits in the project.
* generate project versions: to generate sample versions.
* detect clones
* collect refactor clones
* extract features

You can run detectClones.sh for jfreechart project, or you can change the projectName and project repository for your own need.

If you want to view the source code, *CREC* was developed as an [Eclipse](http://www.eclipse.org/mars/) Java Project, you can simply **import** the project into your workspace(in the code folder).

## IV.  Structure of the project
```powershell
  |--- README.md           :  user guidance
  |--- codes               :  source code
  |--- runnable            :  shell for preparation
  |--- features            :  features extracted by CREC
  |--- refactorInstances   :  refactor instances collected by CREC
  |--- unrefactorInstances :  not refactor instances collected by CREC
  |--- results             :  machine learning results in paper

```

----


<u> All suggestions are welcomed.</u>
