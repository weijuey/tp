---
layout: page
title: Yoong Wei Jue's Project Portfolio Page
---

### Project: d'Intérieur

d'Intérieur is a desktop app for interior designers to manage their contacts and projects. The designer interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 26 kLoC.

Given below are my contributions to the project.

* **New Feature**: Added notes feature
  * What it does: allows the user to add and delete notes to each contact, to be referenced in the future.
  * Justification: This feature is flexible in utility, allowing the user to save any information they deem important, thus enhancing the product.
  * Highlights: Implementing the feature required understanding how modification of internal data is done. Integrating a variably-sized UI element also required careful consideration of pros and cons of different solutions.
  * Credits: UI design decisions were made as a team.

* **New Feature**: Added detailed view feature
  * What it does: allows the user to view a contact in detail, which the app will switch to a more focused view.
  * Justification: This feature enables the user to do more focused work with one particular contact, especially because some information is only fully available to view by using this feature. It can allow adding as many new information to a contact as desired.
  * Highlights: The feature involved multiple components of the application and adding new interfaces to each of them to interact, thus it required a good understanding of their current interactions. Adding new capabilities to current commands also required understanding their existing behaviour.
  * Credits: Idea to communicate to MainWindow the panel view to show was first implemented by Ezekiel for his Images feature, and was adapted from his implementation. Final design of the detailed contact view was also refined by Ezekiel. Normal command execution path of new features were first implemented by team members.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2122s2.github.io/tp-dashboard/?search=weijuey&breakdown=true&sort=groupTitle&sortWithin=title&since=2022-02-18&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Project management**:
  * Managed releases `v1.2` - `v1.4` (5 releases) on GitHub
  * Added target user profile, value proposition and glossary to Developer Guide

* **Enhancements to existing features**:
  * Update existing commands to work in the new detailed view feature mentioned above, with simpler command formats.

* **Documentation**:
  * User Guide:
    * Added documentation for the features `delete` and `find` [\#72]()
    * Did cosmetic tweaks to existing documentation of features `clear`, `exit`: [\#74]()
  * Developer Guide:
    * Added implementation details of the `delete` feature.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [\#12](), [\#32](), [\#19](), [\#42]()
  * Contributed to forum discussions (examples: [1](), [2](), [3](), [4]())
  * Reported bugs and suggestions for other teams in the class (examples: [1](), [2](), [3]())
  * Some parts of the history feature I added was adopted by several other class mates ([1](), [2]())
  

* _{you can add/remove categories in the list above}_
