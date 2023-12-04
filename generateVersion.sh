#!/bin/bash

# Regras para geração e versão semântica
# major: breaking change (obrigatório o uso da exclamação)
# minor: feat, style
# path: build, fix, perf, refactor, revert

GERAR_VERSAO=$1
echo "Gerar versao: $GERAR_VERSAO"

ULTIMA_TAG=$(git describe --tags --abbrev=0 --always)
echo "Ultima tag: #$ULTIMA_TAG#"
PATTERN="^[0-9]+\.[0-9]+\.[0-9]+$"

increment_version() {
    local version=$1
    local increment=$2
    local major=$(echo $version | cut -d. -f1)
    local minor=$(echo $version | cut -d. -f2)
    local patch=$(echo $version | cut -d. -f3)

    if [ "$increment" == "major" ]; then
        major=$((major + 1))
        minor=0
        patch=0
    elif [ "$increment" == "minor" ]; then
        minor=$((minor + 1))
        patch=0
    elif [ "$increment" == "patch" ]; then
        patch=$((patch + 1))
    fi

    echo "${major}.${minor}.${patch}"
}

push_newversion() {
    local new_version=$1
    if [ "$GERAR_VERSAO" == "true" ]; then
        echo "Gerando nova versão..."
        git tag $new_version
        #git push origin $new_version
    else
        echo "Para gerar uma nova versão, deve enviar o argumento \"true\""
    fi
}

if [[ $ULTIMA_TAG =~ $PATTERN ]]; then
    git log $ULTIMA_TAG..HEAD --no-decorate --pretty=format:"%s" > messages.txt
    echo " " >> messages.txt
    new_version=$ULTIMA_TAG
    increment_type=""

    while read message; do
        if [[ $message =~ ".*!" ]]; then
            echo "Tem breaking change: $message"
            increment_type="major"
            break
        elif [[ $message =~ "feat" ]] || [[ $message =~ "style" ]]; then
            echo "Nova funcionalidade ou mudança de estilo: $message"
            if [ -z "$increment_type" ] || [ "$increment_type" == "patch" ]; then
                increment_type="minor"
            fi
        elif [[ $message =~ "fix" ]] || [[ $message =~ "build" ]] || [[ $message =~ "perf" ]] || [[ $message =~ "refactor" ]] || [[ $message =~ "revert" ]]; then
            echo "Correção de bug ou outras modificações: $message"
            if [ -z "$increment_type" ]; then
                increment_type="patch"
            fi
        fi
    done < messages.txt

    rm messages.txt

    if [ -n "$increment_type" ]; then
        new_version=$(increment_version $ULTIMA_TAG $increment_type)
        echo "Nova versão: $new_version"
        push_newversion $new_version
    else
        echo "Nenhuma alteração que requer incremento de versão."
    fi
else
    echo "Nova versão: 0.0.0"
    push_newversion "0.0.0"
fi
