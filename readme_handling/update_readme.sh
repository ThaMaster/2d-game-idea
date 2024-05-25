#!/bin/bash

# Change working directory to the directory where the script resides
cd "$(dirname "$0")"

# Specify the folder containing Markdown files
markdown_folder="logs"

# Find the newest Markdown files in the folder based on file names
newest_files=$(ls -1 "$markdown_folder"/*.md | sort -r | head -n 1)

# Save the content of the main README.md
main_content=$(cat main.md)

# Combine the main content and the content of the newest Markdown files into README.md
echo "$main_content" > ../README.md
# Adds a space 
echo  "" >> ../README.md
for file in $newest_files; do
    cat "$file" >> ../README.md
done
