// openaiService.js

const openaiApiKey = import.meta.env.VITE_APP_OPENAI_API_KEY;
console.log(openaiApiKey); 

export async function fetchOpenAIResponse(prompt) {
  const response = await fetch('https://api.openai.com/v1/completions', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${openaiApiKey}`
    },
    body: JSON.stringify({
      model: 'text-davinci-003',
      prompt: prompt,
      max_tokens: 100,
    }),
  });

  const data = await response.json();
  return data;
}

