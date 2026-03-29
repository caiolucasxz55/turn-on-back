INSERT INTO genres (name, slug) VALUES
('alternative', 'alternative'),
('acid jazz', 'acid-jazz'),
('acid rock', 'acid-rock'),
('art rock', 'art-rock'),
('axé', 'axe'),
('bedroom pop', 'bedroom-pop'),
('blues moderno', 'blues-moderno'),
('blues rock', 'blues-rock'),
('boom bap', 'boom-bap'),
('bossa nova', 'bossa-nova'),
('britpop', 'britpop'),
('cold wave', 'cold-wave'),
('country blues', 'country-blues'),
('darkwave', 'darkwave'),
('deathrock', 'deathrock'),
('doo-wop', 'doo-wop'),
('doom metal', 'doom-metal'),
('dream pop', 'dream-pop'),
('ebm', 'ebm'),
('experimental', 'experimental'),
('folk', 'folk'),
('folk pop', 'folk-pop'),
('funk rock', 'funk-rock'),
('garage rock', 'garage-rock'),
('glam rock', 'glam-rock'),
('gótico do sul', 'gotico-do-sul'),
('hard rock', 'hard-rock'),
('heavy metal', 'heavy-metal'),
('hip hop alternativo', 'hip-hop-alternativo'),
('hip hop brasileiro', 'hip-hop-brasileiro'),
('indie', 'indie'),
('indie folk', 'indie-folk'),
('indie rock', 'indie-rock'),
('indie pop', 'indie-pop'),
('indie soul', 'indie-soul'),
('jazz brasileiro', 'jazz-brasileiro');

INSERT INTO songs (title, artist, album, duration, cover_url, preview_url, genre_id)

SELECT 'Out Getting Ribs', 'King Krule', '6 Feet Beneath the Moon', 256, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Empty And Silent (feat. King Krule)', 'Mount Kimbie;King Krule', 'The Sunset Violent', 367, NULL, NULL, id FROM genres WHERE slug = 'experimental'
UNION ALL
SELECT 'I''VE BEEN EVIL', 'SPIRIT OF THE BEEHIVE', 'YOU’LL HAVE TO LOSE SOMETHING', 154, NULL, NULL, id FROM genres WHERE slug = 'experimental'
UNION ALL
SELECT 'Cigarette Packet', 'Sorry', 'Separate / Cigarette Packet', 138, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Blue Train Lines', 'Mount Kimbie;King Krule', 'Love What Survives', 250, NULL, NULL, id FROM genres WHERE slug = 'experimental'
UNION ALL
SELECT 'La Lune', 'King Krule', 'The OOZ', 257, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Cracking', 'Crumb', 'Jinx', 111, NULL, NULL, id FROM genres WHERE slug = 'indie-pop'
UNION ALL
SELECT 'Bless Your Soul', 'The Bones of J.R. Jones', 'Spirit''s Furnace', 212, NULL, NULL, id FROM genres WHERE slug = 'folk'
UNION ALL
SELECT 'Like Him (feat. Lola Young)', 'Tyler, The Creator;Lola Young', 'CHROMAKOPIA', 278, NULL, NULL, id FROM genres WHERE slug = 'hip-hop'
UNION ALL
SELECT 'Slush Puppy', 'King Krule', 'The OOZ', 162, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Black Water', 'Timber Timbre', 'Creep On Creepin'' On', 359, NULL, NULL, id FROM genres WHERE slug = 'folk'
UNION ALL
SELECT '2 DOGS 1 LEASH', 'Noah Guy', '2 DOGS 1 LEASH', 162, NULL, NULL, id FROM genres WHERE slug = 'alternative'
UNION ALL
SELECT 'In Your Eyes', 'BADBADNOTGOOD;Charlotte Day Wilson', 'IV', 247, NULL, NULL, id FROM genres WHERE slug = 'jazz'
UNION ALL
SELECT 'Tearjerker', 'Small Forward', 'Tearjerker', 250, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Flimsier', 'King Krule', 'Space Heavy', 239, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Seagirl', 'King Krule;Raveena', 'Space Heavy', 202, NULL, NULL, id FROM genres WHERE slug = 'indie-pop'
UNION ALL
SELECT 'Ghostride', 'Crumb', 'Jinx', 126, NULL, NULL, id FROM genres WHERE slug = 'indie-pop'
UNION ALL
SELECT 'Only You... And Everybody Else', 'Potwash', 'Only You... And Everybody Else', 160, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT '5 Dollar Pony Rides', 'Mac Miller', 'Balloonerism', 222, NULL, NULL, id FROM genres WHERE slug = 'hip-hop'
UNION ALL
SELECT 'Almost Fantasy', 'Fog Lake', 'Almost Fantasy', 132, NULL, NULL, id FROM genres WHERE slug = 'indie-pop'
UNION ALL
SELECT 'Keep', 'Rum Jungle', 'Keep', 259, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'We Should Hang', 'Willie J Healey', 'People and Their Dogs', 217, NULL, NULL, id FROM genres WHERE slug = 'indie-pop'
UNION ALL
SELECT 'Bad Moves', 'The Bones of J.R. Jones', 'Bad Moves', 246, NULL, NULL, id FROM genres WHERE slug = 'folk'
UNION ALL
SELECT 'Taking Care of the Dark', 'Doc Aquatic', 'Blue Hour Yawn', 190, NULL, NULL, id FROM genres WHERE slug = 'experimental'
UNION ALL
SELECT 'Starstuck', 'Potwash', 'Starstuck', 135, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Sambinha', 'Exclusive Os Cabides', 'Roubaram Tudo', 150, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Caroline', 'Lowertown', 'Friends', 243, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'WET SOCKS', 'Richy Mitch & The Coal Miners', 'Subliming', 147, NULL, NULL, id FROM genres WHERE slug = 'folk'
UNION ALL
SELECT 'Are we in Oz', 'Sound Bullet', 'Home Ghosts', 225, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Cut from a ceiling fan', 'Passion Mango', 'Garden Party', 209, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Time Shrinks', 'Arcy Drive', 'Time Shrinks', 216, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Seahorse', 'Horsey;King Krule', 'Debonair', 258, NULL, NULL, id FROM genres WHERE slug = 'experimental'
UNION ALL
SELECT '$10', 'Good Morning', 'Prize//Reward', 89, NULL, NULL, id FROM genres WHERE slug = 'indie-pop'
UNION ALL
SELECT 'Arise Dear Brother', 'Archy Marshall;King Krule', 'A New Place 2 Drown', 174, NULL, NULL, id FROM genres WHERE slug = 'experimental'
UNION ALL
SELECT 'Cosmic Freeway', 'Yeek;Max of Homestead', 'Sebastian', 362, NULL, NULL, id FROM genres WHERE slug = 'hip-hop'
UNION ALL
SELECT 'Rest My Chemistry', 'Interpol', 'Our Love To Admire', 301, NULL, NULL, id FROM genres WHERE slug = 'alternative'
UNION ALL
SELECT 'Safe and Sound', 'Mr Floyd Larry', 'Grungy Bungee', 278, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Mama Jay', 'Frizzy P & Mr Cole', 'Ladi Dadi', 225, NULL, NULL, id FROM genres WHERE slug = 'hip-hop'
UNION ALL
SELECT 'Only To Live In Your Memories', 'Night Moves', 'Pennied Days', 265, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'FTA', 'Whitney', 'FTA b/w Far, Far Away', 147, NULL, NULL, id FROM genres WHERE slug = 'indie-pop'
UNION ALL
SELECT '9', 'Dean Blunt;Panda Bear', 'ZUSHI', 87, NULL, NULL, id FROM genres WHERE slug = 'experimental'
UNION ALL
SELECT 'Mushroom Cloud', 'Tempesst', 'Must Be a Dream', 258, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Sex Tourists', 'French Kicks', 'Swimming', 211, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'How To Fly', 'Sticky Fingers', 'Caress Your Soul', 202, NULL, NULL, id FROM genres WHERE slug = 'alternative'
UNION ALL
SELECT 'When It Lands', 'Rainbow Kitten Surprise', 'How to: Friend, Love, Freefall', 269, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Tessellate', 'alt-J', 'An Awesome Wave', 182, NULL, NULL, id FROM genres WHERE slug = 'alternative'
UNION ALL
SELECT 'Alien Blues', 'Vundabar', 'Gawk', 155, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Not Allowed', 'TV Girl', 'Who Really Cares', 167, NULL, NULL, id FROM genres WHERE slug = 'indie-pop'
UNION ALL
SELECT 'Riptide', 'Vance Joy', 'Dream Your Life Away', 204, NULL, NULL, id FROM genres WHERE slug = 'folk'
UNION ALL
SELECT 'Mykonos', 'Fleet Foxes', 'Sun Giant', 275, NULL, NULL, id FROM genres WHERE slug = 'folk'
UNION ALL
SELECT 'Weird Fishes / Arpeggi', 'Radiohead', 'In Rainbows', 318, NULL, NULL, id FROM genres WHERE slug = 'alternative'
UNION ALL
SELECT 'Come Back to Earth', 'Mac Miller', 'Swimming', 161, NULL, NULL, id FROM genres WHERE slug = 'hip-hop'
UNION ALL
SELECT 'Planet Caravan (2012 Remaster)', 'Black Sabbath', 'Paranoid', 267, NULL, NULL, id FROM genres WHERE slug = 'metal'
UNION ALL
SELECT 'Roman Holiday', 'Fontaines D.C.', 'Skinty Fia', 268, NULL, NULL, id FROM genres WHERE slug = 'indie-rock'
UNION ALL
SELECT 'Cut My Lip', 'Twenty One Pilots', 'Trench', 282, NULL, NULL, id FROM genres WHERE slug = 'alternative'
UNION ALL
SELECT 'Come A Little Closer', 'Cage The Elephant', 'Melophobia', 229, NULL, NULL, id FROM genres WHERE slug = 'alternative';