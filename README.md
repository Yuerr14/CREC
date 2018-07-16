# CREC

## I. Introduction

This project is built to automatically recommend clones for refactoring, which leverages both the present status and the past evolution of clones. 

* This project is implemented for the research paper “Automatic Clone Recommendation for Refactoring Based on the Present and the Past” accepted by [ICSME 2018](https://icsme2018.github.io/).*

## II. Environment

* OS: GNU Linux (Tested on Ubuntu 14.04 LTS) 
* JDK: JDK1.8

## III. How to run

Originally, *CREC* was developed as an [Eclipse](http://www.eclipse.org/mars/) Java Project, you can simply **import** the project into your workspace and the class `cofix.main.Main` is the entry of the whole program.

## IV.  Structure of the project
```powershell
  |--- README.md         :  user guidance
  |--- codes             :  source code
  |--- features          :  features extracted by CREC
  |--- refactorInstances :  refactor instances collected by CREC
  |--- results           :  machine learning results in paper

```

----


<u> All suggestions are welcomed.</u>