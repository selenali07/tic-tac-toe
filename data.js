fetch('https://mortgagebanker.bmoharris.com/il/naperville/jb-222936/')
  .then(response => response.text())
  .then(html => {
    const parser = new DOMParser();
    const doc = parser.parseFromString(html, 'text/html');
    
    // Access the desired elements by their class name or ID
    const email = doc.querySelector('#w2gilocalmain > div > div.address_map_wrapper > section.address_block > div.address_block_left > div > div.desktop > div:nth-child(2) > a > span').textContent.trim();
    const description = doc.querySelector('.assist_inner').textContent.trim();
    const specialties = doc.querySelector('#w2gilocalmain > div > div.address_map_wrapper > div > div > div:nth-child(4) > div').textContent.trim();
    const background = doc.querySelector('#w2gilocalmain > div > div.address_map_wrapper > div > div > div:nth-child(5) > div').textContent.trim();
    
    // Create a JavaScript object with the scraped content
    const data = {
      "Email": email.slice(7),
      "Description": description,
      "Specialties": specialties,
      "Background": background
    };
    
    // Convert the JavaScript object to JSON
    const json = JSON.stringify(data);
    
    console.log(json);
  })
  .catch(error => console.error(error));
