
# Concrete Project Template

Este repositório inclui um Projeto de Referência e o Projeto de Plugin-Wizard.

* O Projeto de Referência é um app para carregar repositórios Kotlin do GitHub aplicando os conceitos de modularização, arquitetura MVVM, entre outras Libs e padrões de projeto estudadas no projeto de P&D 2ª Edição.

* O Projeto de Plugin-Wizard é um plugin para Android Studio ou IntelliJ que gera um projeto com as configurações personalizáveis do que foi pesquisado no projeto de P&D 2ª Edição que está exposto no [repositório android-docs-reference](https://github.com/concretesolutions/android-docs-reference).
 
 ![MicrosoftTeams-image (1)](https://user-images.githubusercontent.com/80279421/117838759-fb68a880-b250-11eb-8feb-135197b626aa.png)




## Decisões Técnicas Tomadas

* MVVM
* Módulos de Feature
* Glide
* Room
* Koin
* Architecture Components
* Retrofit
* Material Components
* Version Catalog (Gradle 7.0)
* Type Safe Project Accessors (Gradle 7.0)


## Estruturação dos módulos

``` 
├── app
├── core
│   ├── base
│   ├── database
│   ├── model
│   └── network
└── feature
    └── pullrequests
```


## Como faço para contribuir?

Dê uma olhada nas Issues e abra um Pull Request para gente! 😃


## Como entender melhor o que está neste projeto?

* O repositório android-docs-reference reúne referências técnicas de Android com análises, comparações entre bibliotecas, padrões de projetos e recomendações técnicas que podem te ajudar escolher a melhor solução para seu projeto:

https://github.com/concretesolutions/android-docs-reference


## Blog Concrete

**Este repositório é uma iniciativa da Concrete Solutions!** para conferir mais iniciativas como esta confira nosso Blog e Codelabs:

Blog - https://medium.com/concretebr/tagged/concrete

Codelabs -> http://concretesolutions.github.io/
